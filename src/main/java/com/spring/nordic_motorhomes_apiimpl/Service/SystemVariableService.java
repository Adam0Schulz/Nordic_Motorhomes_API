package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.SystemVariable;
import com.spring.nordic_motorhomes_apiimpl.Repository.SystemVariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Adam
@Service
public class SystemVariableService {

    // Dependencies
    private final SystemVariableRepository systemVariableRepo;

    @Autowired
    public SystemVariableService(SystemVariableRepository systemVariableRepo) {
        this.systemVariableRepo = systemVariableRepo;
    }

    // Save a variable
    public SystemVariable save(SystemVariable systemVariable) {
        return systemVariableRepo.save(systemVariable);
    }

    // Get a variable
    public Optional<SystemVariable> get(Long id) {
        return systemVariableRepo.findById(id);
    }

    // Get a variables value - by name
    public double get(String variable) {
        return systemVariableRepo.findByName(variable).map(SystemVariable::getValue).orElse(0.0);
    }

    // Get all variables
    public List<SystemVariable> getAll () {
        return systemVariableRepo.findAll();
    }

    // Update a variable
    public Optional<SystemVariable> update(Long id, SystemVariable variable) {
        variable.setID(id);
        return !(systemVariableRepo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(variable));
    }

    // Delete a variable
    public void delete(Long id) { systemVariableRepo.deleteById(id); }


}
