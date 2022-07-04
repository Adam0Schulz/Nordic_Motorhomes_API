package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Customer;
import com.spring.nordic_motorhomes_apiimpl.Entity.Status;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import com.spring.nordic_motorhomes_apiimpl.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService extends GeneralService<Status> {

    @Autowired
    public StatusService(GeneralRepository<Status> repo) {
        super(repo);
    }


}
