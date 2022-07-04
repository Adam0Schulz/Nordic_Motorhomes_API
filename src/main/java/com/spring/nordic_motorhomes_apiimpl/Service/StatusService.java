package com.spring.nordic_motorhomes_apiimpl.Service;


import com.spring.nordic_motorhomes_apiimpl.Entity.Status;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatusService extends GeneralService<Status> {

    @Autowired
    public StatusService(GeneralRepository<Status> repo) {
        super(repo);
    }

}
