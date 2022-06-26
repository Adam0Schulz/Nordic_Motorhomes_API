package com.spring.nordic_motorhomes_apiimpl.Repository;

import com.spring.nordic_motorhomes_apiimpl.Entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Wanesa
@Repository
public interface SeasonRepository extends JpaRepository<Season,Long> {
}
