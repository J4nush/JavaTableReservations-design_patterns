package pl.pollub.javatablereservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.javatablereservations.entity.Status;
import pl.pollub.javatablereservations.repository.StatusRepository;

import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(
            StatusRepository statusRepository
    ) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatusList() {
        return this.statusRepository.findAll();
    }



}
