package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Motorhome;
import com.spring.nordic_motorhomes_apiimpl.Entity.Status;
import com.spring.nordic_motorhomes_apiimpl.Entity.SystemVariable;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import com.spring.nordic_motorhomes_apiimpl.Repository.MotorhomeRepository;
import com.spring.nordic_motorhomes_apiimpl.Service.StatusService;
import com.spring.nordic_motorhomes_apiimpl.Service.SystemVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotorhomeService extends GeneralService<Motorhome> {

    // Dependencies
    private final SystemVariableService systemVariableSer;
    private final StatusService statusSer;

    @Autowired
    public MotorhomeService(GeneralRepository<Motorhome> repo,
                            SystemVariableService systemVariableSer,
                            StatusService statusSer) {
        super(repo);
        this.systemVariableSer = systemVariableSer;
        this.statusSer = statusSer;
    }

    // Check availability on a given date
    public boolean isAvailable(Long id, LocalDate date) {
        Optional<Motorhome> motorhome = get(id);

        double buffer = systemVariableSer.get("motorhome availability buffer");
        long count =  motorhome.isEmpty() ? 1 : motorhome.orElseThrow().getBookings()
                                .stream().filter(booking ->
                                booking.isContainingDate(date, buffer))
                                .count();
        return count == 0;
    }

    // Check availability during given time frame
    public boolean isAvailable(Long id, LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates =  startDate.datesUntil(endDate).collect(Collectors.toList());
        long count = dates.stream().filter(date -> isAvailable(id, date)).count();
        return count == 0;
    }

    // Get available motorhomes on a given date
    public List<Motorhome> getAllAvailable(LocalDate date) {
        List<Motorhome> motorhomes = getAll();
        return motorhomes.stream().filter(motorhome ->
                        isAvailable(motorhome.getID(), date))
                .collect(Collectors.toList());
    }

    // Get available motorhomes during given time frame
    public List<Motorhome> getAllAvailable(LocalDate startDate, LocalDate endDate) {
        List<Motorhome> motorhomes = getAll();
        return motorhomes.stream().filter(motorhome ->
                        isAvailable(motorhome.getID(), startDate, endDate))
                .collect(Collectors.toList());
    }

    // Update/change status
    public Optional<Motorhome> updateStatus(Long motorhomeID, Long statusID) {
        Optional<Status> status = statusSer.get(statusID);
        Optional<Motorhome> motorhome = get(motorhomeID);

        if(status.isEmpty() || motorhome.isEmpty()) return Optional.empty();

        motorhome.get().setStatus(status.get());

        return motorhome;
    }

}
