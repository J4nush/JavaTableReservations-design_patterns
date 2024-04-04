package pl.pollub.javatablereservations.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String apiName;
    private String name;

    public Status(String apiName, String name) {
        this.apiName = apiName;
        this.name = name;
    }

    public Status() {}

    public Status(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getApiName() {
        return apiName;
    }

    public String getName() {
        return name;
    }
}
