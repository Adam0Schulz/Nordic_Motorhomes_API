package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Season;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import com.spring.nordic_motorhomes_apiimpl.Repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Adam
@Service
public class SeasonService extends GeneralService<Season>{

    @Autowired
    public SeasonService(GeneralRepository<Season> repo) {
        super(repo);
    }


    // Get season - returns a season on given date (start date of the booking)
    public Optional<Season> get(LocalDate bookingStartDate) {

        // Set up
        Stream <Season> seasons = getAll().stream();
        int bookStartM = bookingStartDate.getMonthValue();

        List<Season> season = seasons.filter(season1 ->
                        season1.getStartMonth() <= bookStartM
                                && season1.getStartMonth() > bookStartM)
                .collect(Collectors.toList());

        return season.size() != 1 ? Optional.empty() : Optional.of((Season) season);

    }

}
