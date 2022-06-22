package com.spring.nordic_motorhomes_apiimpl.Repository;

import com.spring.nordicmotorhomes.Entity.Extra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Wanesa
@Repository
public interface ExtraRepository extends JpaRepository<Extra, Long> {
}
