package pl.pollub.javatablereservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.javatablereservations.entity.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<Reservation> findAllByDate(Date date);

    Optional<Reservation> findReservationByTableIdAndDate(UUID tableId, Date date);

}
