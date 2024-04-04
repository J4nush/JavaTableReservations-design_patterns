package pl.pollub.javatablereservations.controller;

import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.javatablereservations.dto.LoginDto;
import pl.pollub.javatablereservations.dto.LoginResponseDto;
import pl.pollub.javatablereservations.factory.ResponseFactory;
import pl.pollub.javatablereservations.service.AuthService;
import pl.pollub.javatablereservations.service.ServiceFacade;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/")
public class AuthController {

    private final ServiceFacade serviceFacade;

    @Autowired
    public AuthController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @PostMapping(value = "login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        Optional<LoginResponseDto> loginResponseDto = this.serviceFacade.proxyLogin(loginDto);

        if (loginResponseDto.isPresent()) {
            ResponseFactory.ok(loginResponseDto);
        }
        return ResponseFactory.loginFailed();
    }

    @PostMapping(value = "refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(@RequestHeader("Authorization") String sessionId) {
        Optional<LoginResponseDto> loginResponseDto = this.serviceFacade.proxyRefreshToken(UUID.fromString(sessionId));

        if (loginResponseDto.isPresent()) {
            ResponseFactory.ok(loginResponseDto);
        }
        return ResponseFactory.loginFailed();
    }
}
