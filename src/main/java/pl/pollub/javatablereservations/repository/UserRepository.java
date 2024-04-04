package pl.pollub.javatablereservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.javatablereservations.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLoginAndPassword(String login, String password);

}
