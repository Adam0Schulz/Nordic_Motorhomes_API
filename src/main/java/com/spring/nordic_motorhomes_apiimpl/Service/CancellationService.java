package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Cancellation;
import com.spring.nordic_motorhomes_apiimpl.Entity.CancellationFee;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import com.spring.nordic_motorhomes_apiimpl.Service.CancellationFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancellationService extends GeneralService<Cancellation> {

    // Dependencies
    private final CancellationFeeService feeService;

    @Autowired
    public CancellationService(GeneralRepository<Cancellation> repo,
                               CancellationFeeService feeService) {
        super(repo);
        this.feeService = feeService;
    }

    // Save a cancellation
    public Cancellation save(Cancellation cancellation) {
        if(cancellation.getFee().isPresent()) return repo.save(cancellation);

        cancellation.setFee(feeService.selectFee(cancellation.getBooking()));

        return cancellation;
    }



}
