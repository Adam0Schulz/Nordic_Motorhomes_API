package com.spring.nordic_motorhomes_apiimpl.Repository;

import com.spring.nordic_motorhomes_apiimpl.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
}