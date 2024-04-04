package pl.pollub.javatablereservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pollub.javatablereservations.entity.Table;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TableRepository extends JpaRepository<Table, UUID> {

    Optional<Table> getTableById(UUID id);

    @Query(value = """
    SELECT id, seats, status_id
    FROM tables 
    WHERE id NOT IN (
        SELECT table_id 
        FROM reservation 
        WHERE status_id IN (
                SELECT id 
                FROM status 
                WHERE api_name = 'RES_NEW' OR api_name = 'RES_ACC')
    ) AND status_id = (
        SELECT id 
        FROM status
        WHERE api_name = 'TABLE_OPEN'
    )
    """, nativeQuery = true)
    List<Table> getAvailableTableList();

    @Query(value = """
    SELECT id, seats, status_id 
    FROM tables 
    WHERE id NOT IN (
        SELECT table_id 
        FROM reservation 
        WHERE YEAR(date) = :year AND 
              MONTH(date) = :month AND 
              DAYOFMONTH(date) = :day AND 
              status_id IN (
                SELECT id 
                FROM status 
                WHERE api_name = 'RES_NEW' OR api_name = 'RES_ACC')
    ) AND status_id = (
        SELECT id 
        FROM status
        WHERE api_name = 'TABLE_OPEN'
    )
    """, nativeQuery = true)
    List<Table> getAvailableTableList(int day, int month, int year);

}
