package pl.pollub.javatablereservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pollub.javatablereservations.dto.*;
import pl.pollub.javatablereservations.entity.Reservation;
import pl.pollub.javatablereservations.entity.Table;
import pl.pollub.javatablereservations.entity.Status;
import pl.pollub.javatablereservations.factory.StatusFlyweightFactory;
import pl.pollub.javatablereservations.service.AuthService;
import pl.pollub.javatablereservations.service.ReservationService;
import pl.pollub.javatablereservations.service.StatusService;
import pl.pollub.javatablereservations.service.TableService;
import pl.pollub.javatablereservations.Constants;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ServiceFacade {

    private final AuthService authService;
    private final ReservationService reservationService;
    private final TableService tableService;
    private final StatusService statusService;

    private final AuthServiceProxy authServiceProxy;

    @Autowired
    public ServiceFacade(AuthService authService, ReservationService reservationService, TableService tableService, StatusService statusService, AuthServiceProxy authServiceProxy) {
        this.authService = authService;
        this.reservationService = reservationService;
        this.tableService = tableService;
        this.statusService = statusService;
        this.authServiceProxy = authServiceProxy;
    }

    public Optional<LoginResponseDto> proxyLogin(LoginDto loginDto) {
        return authServiceProxy.login(loginDto);
    }
    public Optional<LoginResponseDto> login(LoginDto loginDto) {
        return authService.login(loginDto);
    }

    public Optional<LoginResponseDto> proxyRefreshToken(UUID sessionId) {
        return authServiceProxy.refreshToken(sessionId);
    }
    public Optional<LoginResponseDto> refreshToken(UUID sessionId) {
        return authService.refreshToken(sessionId);
    }

    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }


    @Autowired
    private StatusFlyweightFactory statusFlyweightFactory;
    public void makeReservation(CreateReservationDto createReservationDto) throws ParseException {

        reservationService.makeReservation(createReservationDto);
    }

    public List<Table> getAllTableList() {
        return tableService.getAllTableList();
    }

    public void updateReservationStatus(ChangeReservationDto changeReservationDto, String statusApiNameResAcc) {
        //flyweight
        Status status = statusFlyweightFactory.getStatus(statusApiNameResAcc);
        reservationService.updateReservationStatus(changeReservationDto, status.getApiName());
    }

    public List<Reservation> getAllReservationsByDate(Date parsedDate) {
        return reservationService.getAllReservationsByDate(parsedDate);
    }

    public List<Status> getAllStatusList() {
        return statusService.getAllStatusList();
    }
}