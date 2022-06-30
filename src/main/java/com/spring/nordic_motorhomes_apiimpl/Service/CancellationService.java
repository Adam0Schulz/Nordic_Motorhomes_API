package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Cancellation;
import com.spring.nordic_motorhomes_apiimpl.Entity.CancellationFee;
import com.spring.nordic_motorhomes_apiimpl.Repository.CancellationRepository;
import com.spring.nordic_motorhomes_apiimpl.Service.CancellationFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancellationService {

    // Dependencies
    private final CancellationRepository cancellationRepo;
    private final CancellationFeeService feeService;

    @Autowired
    public CancellationService(CancellationRepository cancellationRepo,
                               CancellationFeeService feeService) {
        this.cancellationRepo = cancellationRepo;
        this.feeService = feeService;
    }

    // Save a cancellation
    public Cancellation save(Cancellation cancellation) {
        if(cancellation.getFee().isPresent()) return cancellationRepo.save(cancellation);
        cancellation.setFee(feeService.selectFee(cancellation.getBooking()));
        return cancellation;
    }

    // Get a cancellation
    public Optional<Cancellation> get(Long id) {
        return cancellationRepo.findById(id);
    }

    // Get all cancellations
    public List<Cancellation> getAll() {
        return cancellationRepo.findAll();
    }

    // Update a cancellation
    public Optional<Cancellation> update(Long id, Cancellation cancellation) {
        cancellation.setID(id);
        return !(cancellationRepo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(cancellation));
    }

    // Delete a cancellation
    public void delete(Long id) { cancellationRepo.deleteById(id); }

}
