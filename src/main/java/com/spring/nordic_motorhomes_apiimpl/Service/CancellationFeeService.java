package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Booking;
import com.spring.nordic_motorhomes_apiimpl.Entity.CancellationFee;
import com.spring.nordic_motorhomes_apiimpl.Repository.CancellationFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

// Adam
@Service
public class CancellationFeeService {

    // Dependencies
    private final CancellationFeeRepository feeRepo;

    @Autowired
    public CancellationFeeService(CancellationFeeRepository feeRepo) {
        this.feeRepo = feeRepo;
    }

    // Select a fee
    public CancellationFee selectFee(Booking booking) {
        LocalDate currentDate = LocalDate.now();
        LocalDate bookingStartDate = booking.getStartDate().toLocalDate();
        int days = (int) ChronoUnit.DAYS.between(currentDate, bookingStartDate);

        List<CancellationFee> fees = feeRepo.findAll();
        CancellationFee fee = null;

        for(CancellationFee f : fees) {
            if(days < f.getMaxDaysBefore() && days >= f.getMinDaysBefore()) {
                fee = f;
            }
        }
        return fee;
    }

    // Save a fee
    public CancellationFee save(CancellationFee fee) {
        return feeRepo.save(fee);
    }

    // Get a fee
    public Optional<CancellationFee> get(Long id) {
        return feeRepo.findById(id);
    }

    // Get all fees
    public List<CancellationFee> getAll() {
        return feeRepo.findAll();
    }

    // Update a fee
    public Optional<CancellationFee> update(Long id, CancellationFee fee) {
        fee.setID(id);
        return !(feeRepo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(fee));
    }

    // Delete a fee
    public void delete(Long id) { feeRepo.deleteById(id); }
}
