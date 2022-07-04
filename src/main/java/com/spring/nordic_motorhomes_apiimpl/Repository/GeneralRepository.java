package com.spring.nordic_motorhomes_apiimpl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralRepository <T> extends JpaRepository<T, Long> {
}
