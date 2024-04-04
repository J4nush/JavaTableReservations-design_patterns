package pl.pollub.javatablereservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.javatablereservations.Constants;
import pl.pollub.javatablereservations.dto.ChangeReservationDto;
import pl.pollub.javatablereservations.dto.CreateReservationDto;
import pl.pollub.javatablereservations.entity.Reservation;
import pl.pollub.javatablereservations.entity.Status;
import pl.pollub.javatablereservations.entity.Table;
import pl.pollub.javatablereservations.repository.ReservationRepository;
import pl.pollub.javatablereservations.repository.StatusRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, StatusRepository statusRepository) {
        this.reservationRepository = reservationRepository;
        this.statusRepository = statusRepository;
    }

    public List<Reservation> getAllReservations() {
        return this.reservationRepository.findAll();
    }

    public List<Reservation> getAllReservationsByDate(Date date) {
        return this.reservationRepository.findAllByDate(date);
    }

    public Boolean isThereAReservation(UUID tableId, Date date) {
        return this.reservationRepository.findReservationByTableIdAndDate(tableId, date).isPresent();
    }

    public void makeReservation(CreateReservationDto createReservationDto) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        UUID tableId = createReservationDto.getTableId();
        Date reservationDate = dateFormatter.parse(createReservationDto.getDate());
        String email = createReservationDto.getEmail();
        int hour = createReservationDto.getHour();

        if (!this.isThereAReservation(tableId, reservationDate)) {
            Status status = this.statusRepository.findStatusByApiName(Constants.STATUS_API_NAME_RES_NEW).orElseThrow();

            this.reservationRepository.save(new Reservation(
                    new Table(tableId),
                    reservationDate,
                    hour,
                    email,
                    status
            ));

        } else {
            throw new IllegalStateException("Reservation exists");
        }
    }

    public void updateReservationStatus(ChangeReservationDto reservationDto, String statusApiName) {
        Status status = this.statusRepository.findStatusByApiName(statusApiName).orElseThrow();

        this.reservationRepository.save(new Reservation(
                reservationDto.getReservationId(),
                status
        ));
    }

}
