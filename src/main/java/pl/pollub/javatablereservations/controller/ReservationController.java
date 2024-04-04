package pl.pollub.javatablereservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pollub.javatablereservations.Constants;
import pl.pollub.javatablereservations.dto.ChangeReservationDto;
import pl.pollub.javatablereservations.dto.CreateReservationDto;
import pl.pollub.javatablereservations.entity.Reservation;
import pl.pollub.javatablereservations.service.ReservationService;
import pl.pollub.javatablereservations.service.ServiceFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ReservationController {

    private final ServiceFacade serviceFacade;

    //Facade
    @Autowired
    public ReservationController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @GetMapping(value = "/all_reservations")
    public List<Reservation> getAllReservations() {
        return this.serviceFacade.getAllReservations();
    }

    @GetMapping(value = "/reservations_by_date")
    public List<Reservation> getAllReservationsByDate(@RequestParam String date) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormatter.parse(date);
        return this.serviceFacade.getAllReservationsByDate(parsedDate);
    }

    @PostMapping(value = "/create_reservation")
    public String getStatusList(@RequestBody CreateReservationDto createReservationDto) {
        try {
            this.serviceFacade.makeReservation(createReservationDto);
        } catch (Exception exc) {
            return "Can't create reservations for this date";
        }
        return "Reservation created";
    }

    @PostMapping(value = "/accept_reservation")
    public void acceptReservation(ChangeReservationDto changeReservationDto) {
        this.serviceFacade.updateReservationStatus(changeReservationDto, Constants.STATUS_API_NAME_RES_ACC);
    }

    @PostMapping(value = "/reject_reservation")
    public void rejectReservation(ChangeReservationDto changeReservationDto) {
        this.serviceFacade.updateReservationStatus(changeReservationDto, Constants.STATUS_API_NAME_RES_CAN);
    }
}
