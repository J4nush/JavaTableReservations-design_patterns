package pl.pollub.javatablereservations.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pollub.javatablereservations.dto.LoginDto;
import pl.pollub.javatablereservations.dto.LoginResponseDto;
import pl.pollub.javatablereservations.repository.IAuthService;

import java.util.Optional;
import java.util.UUID;

//proxy
@Component
public class AuthServiceProxy implements IAuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceProxy.class);

    //facade
    private final ServiceFacade serviceFacade;

    @Autowired
    public AuthServiceProxy(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public Optional<LoginResponseDto> login(LoginDto loginDto) {
        logger.info("Logowanie użytkownika: {}", loginDto.getLogin());
        Optional<LoginResponseDto> response = serviceFacade.login(loginDto);
        if (response.isPresent()) {
            logger.info("Logowanie użytkownika: {} zakończone sukcesem", loginDto.getLogin());
        } else {
            logger.info("Logowanie użytkownika: {} nie powiodło się", loginDto.getLogin());
        }
        return response;
    }

    @Override
    public Optional<LoginResponseDto> refreshToken(UUID sessionId) {
        logger.info("Odświeżanie tokena dla sesji: {}", sessionId);
        return serviceFacade.refreshToken(sessionId);
    }
}
