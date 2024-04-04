package pl.pollub.javatablereservations.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String name;

    public Role() {
    }

    public Role(UUID id) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
