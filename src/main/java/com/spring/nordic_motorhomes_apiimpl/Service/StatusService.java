package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Customer;
import com.spring.nordic_motorhomes_apiimpl.Entity.Status;
import com.spring.nordic_motorhomes_apiimpl.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    //Dependencies
    private final StatusRepository statusRepo;

    @Autowired
    public StatusService(StatusRepository statusRepo) {
        this.statusRepo = statusRepo;
    }


    // Save status
    public Status save(Status status) {
        return statusRepo.save(status);
    }

    // Get status
    public Optional<Status> get(Long id) {
        return statusRepo.findById(id);
    }

    // Get all statuses
    public List<Status> getAll() {
        return statusRepo.findAll();
    }

    // Update status
    public Optional<Status> update(Long id, Status status) {
        status.setID(id);
        return !(statusRepo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(status));
    }

    // Delete status
    public void delete(Long id) { statusRepo.deleteById(id); }
}
