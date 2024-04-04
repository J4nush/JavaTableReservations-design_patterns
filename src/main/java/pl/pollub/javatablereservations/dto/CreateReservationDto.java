package pl.pollub.javatablereservations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateReservationDto {

    @JsonProperty(value = "table_id")
    private UUID tableId;
    private String date;
    private String email;
    private int hour;

    public UUID getTableId() {
        return tableId;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public int getHour() {
        return hour;
    }
}
