package pl.pollub.javatablereservations.repository;

import pl.pollub.javatablereservations.dto.LoginDto;
import pl.pollub.javatablereservations.dto.LoginResponseDto;

import java.util.Optional;
import java.util.UUID;

public interface IAuthService {
    Optional<LoginResponseDto> login(LoginDto loginDto);
    Optional<LoginResponseDto> refreshToken(UUID sessionId);
}
