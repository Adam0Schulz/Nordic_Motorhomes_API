package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Customer;
import com.spring.nordic_motorhomes_apiimpl.Entity.Extra;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraService extends GeneralService<Extra> {

    @Autowired
    public ExtraService(GeneralRepository<Extra> repo) {
        super(repo);
    }


    // Get total price of extras
    public double getTotal(List<Extra> extras) {
        return extras.stream().map(Extra::getPrice).reduce(Double::sum).orElse(0.0);
    }

}
