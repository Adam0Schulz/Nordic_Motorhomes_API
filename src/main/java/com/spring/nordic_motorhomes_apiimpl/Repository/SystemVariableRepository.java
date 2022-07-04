package com.spring.nordic_motorhomes_apiimpl.Repository;

import com.spring.nordic_motorhomes_apiimpl.Entity.SystemVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Wanesa
@Repository
public interface SystemVariableRepository extends GeneralRepository<SystemVariable> {

    Optional<SystemVariable> findByName(String name);
}
