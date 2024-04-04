package pl.pollub.javatablereservations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ChangeReservationDto {

    @JsonProperty(value = "reservation_id")
    private UUID reservationId;

    public UUID getReservationId() {
        return reservationId;
    }

}
