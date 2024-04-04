package pl.pollub.javatablereservations.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.pollub.javatablereservations.entity.Role;
import pl.pollub.javatablereservations.entity.Status;
import pl.pollub.javatablereservations.repository.RoleRepository;
import pl.pollub.javatablereservations.repository.StatusRepository;

@Service
public class DataLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    private final StatusRepository statusRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(
            StatusRepository statusRepository,
            RoleRepository roleRepository
    ) {
        this.statusRepository = statusRepository;
        this.roleRepository = roleRepository;
    }

    public void seed() {
        this.seedStatus();
        this.seedRole();
    }

    private void seedStatus() {
        if (this.statusRepository.findAll().isEmpty()) {
            LOGGER.info("Creating STATUS records...");

            this.statusRepository.save(new Status("TABLE_OPEN", "stolik czynny"));
            this.statusRepository.save(new Status("TABLE_CLOSED", "stolik zamknięty"));
            this.statusRepository.save(new Status("RES_NEW", "Nowa"));
            this.statusRepository.save(new Status("RES_ACC", "Zaakceptowana"));
            this.statusRepository.save(new Status("RES_CAN", "Anulowana"));
            this.statusRepository.save(new Status("RES_END", "Zakończona"));
            this.statusRepository.save(new Status("USER_ACTIVE", "Aktywny"));
            this.statusRepository.save(new Status("USER_BLOCKED", "Zablokowany"));

            LOGGER.info("STATUS records created.");
        }
        else {
            LOGGER.info("Skipping STATUS records creation");
        }
    }

    private void seedRole() {
        if (this.roleRepository.findAll().isEmpty()) {
            LOGGER.info("Creating ROLE records...");

            this.roleRepository.save(new Role("Admin"));
            this.roleRepository.save(new Role("Manager"));
            this.roleRepository.save(new Role("Employee"));

            LOGGER.info("ROLE records created.");
        }
        else {
            LOGGER.info("Skipping ROLE records creation");
        }
    }

}
