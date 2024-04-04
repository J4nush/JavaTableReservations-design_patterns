package pl.pollub.javatablereservations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateUserDto {

    private UUID id;

    private String login;
    private String password;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "role_id")
    private String roleId;

    @JsonProperty(value = "status_id")
    private String statusId;

    public CreateUserDto() {
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getStatusId() {
        return statusId;
    }
}
