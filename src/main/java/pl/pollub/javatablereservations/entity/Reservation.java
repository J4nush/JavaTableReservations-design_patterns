package pl.pollub.javatablereservations.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Table table;

    private Date date;
    private int hour;
    private String email;

    @ManyToOne
    private Status status;

    public Reservation() {}

    public Reservation(UUID id, Status status) {
        this.id = id;
        this.status = status;
    }

    public Reservation(Table table, Date date, int hour, String email, Status status) {
        this.table = table;
        this.date = date;
        this.hour = hour;
        this.email = email;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public Table getTable() {
        return table;
    }

    public Date getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }
}
