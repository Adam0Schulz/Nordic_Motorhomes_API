package com.spring.nordic_motorhomes_apiimpl.Repository;

import com.spring.nordicmotorhomes.Entity.SystemVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Wanesa
@Repository
public interface SystemVariableRepository extends JpaRepository<SystemVariable,Long> {

    Optional<SystemVariable> findByName(String motorhome_availability_buffer);
}
