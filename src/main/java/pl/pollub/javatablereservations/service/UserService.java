package pl.pollub.javatablereservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.javatablereservations.dto.CreateUserDto;
import pl.pollub.javatablereservations.dto.LoginDto;
import pl.pollub.javatablereservations.entity.Role;
import pl.pollub.javatablereservations.entity.Status;
import pl.pollub.javatablereservations.entity.User;
import pl.pollub.javatablereservations.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public void saveUser(CreateUserDto createUserDto) {
        User user = new User(
                createUserDto.getLogin(),
                createUserDto.getPassword(),
                createUserDto.getFirstName(),
                createUserDto.getLastName(),
                new Role(UUID.fromString(createUserDto.getRoleId())),
                new Status(UUID.fromString(createUserDto.getStatusId()))
        );

        this.userRepository.save(user);
    }

    public Optional<User> getUser(LoginDto loginDto) {
        return this.userRepository.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
    }

    public Optional<User> getUser(UUID userId) {
        return this.userRepository.findById(userId);
    }

}
