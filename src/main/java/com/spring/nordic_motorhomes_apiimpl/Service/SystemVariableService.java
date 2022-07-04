package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.SystemVariable;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import com.spring.nordic_motorhomes_apiimpl.Repository.SystemVariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Adam
@Service
public class SystemVariableService extends GeneralService<SystemVariable> {

    private final SystemVariableRepository variableRepo;

    @Autowired
    public SystemVariableService(GeneralRepository<SystemVariable> repo, SystemVariableRepository variableRepo) {
        super(repo);
        this.variableRepo = variableRepo;
    }

    // Get a variables value - by name
    public double get(String variable) {
        return variableRepo.findByName(variable).map(SystemVariable::getValue).orElse(0.0);
    }


}
