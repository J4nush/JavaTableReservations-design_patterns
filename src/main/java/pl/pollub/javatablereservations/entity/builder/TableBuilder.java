package pl.pollub.javatablereservations.entity.builder;

import pl.pollub.javatablereservations.entity.Status;
import pl.pollub.javatablereservations.entity.Table;

public class TableBuilder {

    private final Table table;

    public TableBuilder() {
        this.table = new Table();
    }

    public TableBuilder setSeats(int seats) {
        this.table.setSeats(seats);
        return this;
    }

    public TableBuilder setStatus(Status status) {
        this.table.setStatus(status);
        return this;
    }

    public Table getTable() {
        return table;
    }

}
