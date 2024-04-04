package pl.pollub.javatablereservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.javatablereservations.Constants;
import pl.pollub.javatablereservations.dto.ChangeTableDto;
import pl.pollub.javatablereservations.dto.NewTableDto;
import pl.pollub.javatablereservations.dto.UpdateTableDto;
import pl.pollub.javatablereservations.entity.Status;
import pl.pollub.javatablereservations.entity.Table;
import pl.pollub.javatablereservations.entity.builder.TableBuilder;
import pl.pollub.javatablereservations.repository.StatusRepository;
import pl.pollub.javatablereservations.repository.TableRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TableService {

    private final TableRepository tableRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public TableService(
            TableRepository tableRepository,
            StatusRepository statusRepository
    ) {
        this.tableRepository = tableRepository;
        this.statusRepository = statusRepository;
    }

    public List<Table> getAllTableList() {
        return this.tableRepository.findAll();
    }

    public List<Table> getAllAvailableTableList(Date date) {
        if (date == null) {
            return this.tableRepository.getAvailableTableList();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return this.tableRepository.getAvailableTableList(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR)
        );
    }

    public void newTable(NewTableDto newTableDto) {
        Table table = new TableBuilder()
                .setSeats(newTableDto.getSeats())
                .setStatus(new Status(newTableDto.getStatus()))
                .getTable();
        this.tableRepository.save(table);
    }

    public void openTable(ChangeTableDto changeTableDto) {
        Status openStatus = this.statusRepository.findStatusByApiName(Constants.STATUS_API_NAME_TABLE_OPEN).orElseThrow();
        Table table = this.tableRepository.getTableById(changeTableDto.getTableId()).orElseThrow();

        table.setStatus(openStatus);
        this.tableRepository.save(table);
    }

    public void closeTable(ChangeTableDto changeTableDto) {
        Status openStatus = this.statusRepository.findStatusByApiName(Constants.STATUS_API_NAME_TABLE_CLOSED).orElseThrow();
        System.out.println(changeTableDto.getTableId());
        Table table = this.tableRepository.getTableById(changeTableDto.getTableId()).orElseThrow();

        table.setStatus(openStatus);
        this.tableRepository.save(table);
    }

    public void editTable(UpdateTableDto updateTableDto) {
        Table table = this.tableRepository.getTableById(updateTableDto.getId()).orElseThrow();

        table.setSeats(updateTableDto.getSeats());
        table.setStatus(new Status(updateTableDto.getStatus()));

        this.tableRepository.save(table);
    }

}
