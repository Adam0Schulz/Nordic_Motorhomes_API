package com.spring.nordic_motorhomes_apiimpl.Repository;

import com.spring.nordic_motorhomes_apiimpl.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

// Wanesa
@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByStartDate(Date date);
    List<Booking> findByEndDate(Date date);

    List<Booking> findByMotorhomeID(long ID);
}
