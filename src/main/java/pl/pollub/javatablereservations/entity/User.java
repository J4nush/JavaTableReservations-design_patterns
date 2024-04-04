package pl.pollub.javatablereservations.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String login;
    private String password;
    private String firstName;
    private String lastName;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Status status;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName, Role role, Status status) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
    }

    public User(UUID id, String login, String password, String firstName, String lastName, Role role, Status status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
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

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }
}
