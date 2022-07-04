package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Booking;
import com.spring.nordic_motorhomes_apiimpl.Entity.CancellationFee;
import com.spring.nordic_motorhomes_apiimpl.Repository.CancellationFeeRepository;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

// Adam
@Service
public class CancellationFeeService extends GeneralService<CancellationFee>{

    @Autowired
    public CancellationFeeService(GeneralRepository<CancellationFee> repo) {
        super(repo);
    }

    // Select a fee
    public CancellationFee selectFee(Booking booking) {
        LocalDate currentDate = LocalDate.now();
        LocalDate bookingStartDate = booking.getStartDate().toLocalDate();
        int days = (int) ChronoUnit.DAYS.between(currentDate, bookingStartDate);

        List<CancellationFee> fees = repo.findAll();
        CancellationFee fee = null;

        for(CancellationFee f : fees) {
            if(days < f.getMaxDaysBefore() && days >= f.getMinDaysBefore()) {
                fee = f;
            }
        }
        return fee;
    }
}
