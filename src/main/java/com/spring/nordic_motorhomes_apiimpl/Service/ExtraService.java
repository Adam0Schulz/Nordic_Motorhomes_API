package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Customer;
import com.spring.nordic_motorhomes_apiimpl.Entity.Extra;
import com.spring.nordic_motorhomes_apiimpl.Repository.ExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraService {

    // Dependencies
    private final ExtraRepository extraRepo;

    @Autowired
    public ExtraService(ExtraRepository extraRepo) {
        this.extraRepo = extraRepo;
    }

    // Questionable methods to add - get extras by ids

    // Save a extra
    public Extra save(Extra extra) {
        return extraRepo.save(extra);
    }

    // Get an extra
    public Optional<Extra> get(Long id) {
        return extraRepo.findById(id);
    }

    // Get all extras
    public List<Extra> getAll() {
        return extraRepo.findAll();
    }

    // Get total price of extras
    public double getTotal(List<Extra> extras) {
        return extras.stream().map(Extra::getPrice).reduce(Double::sum).orElse(0.0);
    }

    // Update an extra
    public Optional<Extra> update(Long id, Extra extra) {
        extra.setID(id);
        return !(extraRepo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(extra));
    }

    // Delete an extra
    public void delete(Long id) { extraRepo.deleteById(id); }

}
