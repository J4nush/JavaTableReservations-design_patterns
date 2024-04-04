package pl.pollub.javatablereservations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ChangeTableDto {

    @JsonProperty(value = "table_id")
    private UUID tableId;

    public UUID getTableId() {
        return tableId;
    }
}
