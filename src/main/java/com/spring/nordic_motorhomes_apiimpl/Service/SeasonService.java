package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordicmotorhomes.Entity.Season;
import com.spring.nordicmotorhomes.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// Adam
@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;



    // Get season - returns a season on given date (start date of the booking)
    public Season getSeason(LocalDate bookingStartDate) {

        // Set up
        Season season = null;
        List<Season> seasons = getAllSeasons();

        for (Season s : seasons) {
            int startMonth = s.getStartMonth();
            int endMonth = s.getEndMonth();
            if((startMonth <= bookingStartDate.getMonthValue()) && endMonth > bookingStartDate.getMonthValue()) {
                season = s;
            }
        }
        return season;
    }

    // Get all seasons
    public List<Season> getAllSeasons() { return seasonRepository.findAll(); }

}
