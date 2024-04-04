package pl.pollub.javatablereservations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class LoginResponseDto {

    private UUID token;
    private UUID role;
    @JsonProperty(value = "first_name")
    private String firstName;

    public LoginResponseDto(UUID token, UUID role, String firstName) {
        this.token = token;
        this.role = role;
        this.firstName = firstName;
    }

    public UUID getToken() {
        return token;
    }

    public UUID getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }
}
