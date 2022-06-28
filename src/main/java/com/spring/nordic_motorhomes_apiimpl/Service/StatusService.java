package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    //Dependencies
    private final StatusRepository statusRepo;

    @Autowired
    public StatusService(StatusRepository statusRepo) {
        this.statusRepo = statusRepo;
    }
}
