package pl.pollub.javatablereservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pollub.javatablereservations.dto.LoginDto;
import pl.pollub.javatablereservations.dto.LoginResponseDto;
import pl.pollub.javatablereservations.entity.User;
import pl.pollub.javatablereservations.session.OpaqueSession;
import pl.pollub.javatablereservations.session.Session;

import java.util.*;

@Service
public class AuthService {

    private final Map<UUID, Session> sessionMapById;
    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.sessionMapById = new HashMap<>();
        this.userService = userService;
    }

    public Optional<LoginResponseDto> login(LoginDto loginDto) {
        Optional<User> oUser = this.userService.getUser(loginDto);
        if (oUser.isPresent()) {
            Session session = new OpaqueSession(oUser.get().getId());
            this.sessionMapById.put(session.getId(), session);
            User user = oUser.get();

            return Optional.of(new LoginResponseDto(session.getId(), user.getRole().getId(), user.getFirstName()));
        }
        return Optional.empty();
    }

    public Optional<LoginResponseDto> refreshToken(UUID sessionId) {
        if (this.sessionMapById.containsKey(sessionId)) {
            Session oldSession = this.sessionMapById.get(sessionId);
            User user = this.userService.getUser(oldSession.getUserId()).orElseThrow();

            Session session = oldSession.clone();
            this.sessionMapById.put(session.getId(), session);

            return Optional.of(new LoginResponseDto(session.getId(), user.getRole().getId(), user.getFirstName()));
        }
        return Optional.empty();
    }
}
