package pl.pollub.javatablereservations.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(
        name = "tables"
)
public class Table {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private int seats;

    @ManyToOne
    private Status status;

    public Table() {}

    public Table(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public int getSeats() {
        return seats;
    }

    public Status getStatus() {
        return status;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
