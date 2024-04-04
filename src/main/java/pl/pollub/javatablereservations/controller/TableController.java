package pl.pollub.javatablereservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pollub.javatablereservations.dto.ChangeTableDto;
import pl.pollub.javatablereservations.dto.NewTableDto;
import pl.pollub.javatablereservations.dto.UpdateTableDto;
import pl.pollub.javatablereservations.entity.Table;
import pl.pollub.javatablereservations.service.TableService;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/")
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping(value = "tables")
    public List<Table> getTableList() {
        return this.tableService.getAllTableList();
    }

    @GetMapping(value = "avaible_tables")
    public List<Table> getAvailableTableList(@RequestParam(required = false) String date) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            try {
                return this.tableService.getAllAvailableTableList(formatter.parse(date));
            } catch (Exception exc) {
                throw new IllegalStateException(exc.getMessage());
            }
        }
        else {
            return this.tableService.getAllAvailableTableList(null);
        }
    }

    @PostMapping(value = "new_table")
    public void newTable(@RequestBody NewTableDto table) {
        this.tableService.newTable(table);
    }

    @PostMapping(value = "open_table")
    public void openTable(@RequestBody ChangeTableDto table) {
        this.tableService.openTable(table);
    }

    @PostMapping(value = "close_table")
    public void closeTable(@RequestBody ChangeTableDto table) {
        this.tableService.closeTable(table);
    }

    @PostMapping(value = "edit_table")
    public void editTable(@RequestBody UpdateTableDto table) {
        this.tableService.editTable(table);
    }
}
