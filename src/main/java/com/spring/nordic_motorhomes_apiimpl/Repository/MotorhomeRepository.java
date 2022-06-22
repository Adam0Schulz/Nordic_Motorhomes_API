package com.spring.nordic_motorhomes_apiimpl.Repository;

import com.spring.nordicmotorhomes.Entity.Motorhome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Wanesa
@Repository
public interface MotorhomeRepository extends JpaRepository<Motorhome,Long> {
}
