package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Season;
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
public class SeasonService {

    // Dependencies
    private final SeasonRepository seasonRepo;

    @Autowired
    public SeasonService(SeasonRepository seasonRepo) {
        this.seasonRepo = seasonRepo;
    }


    // Save a season
    public Season save(Season season) {
        return seasonRepo.save(season);
    }

    // Get a season
    public Optional<Season> get(Long id) {
        return seasonRepo.findById(id);
    }

    // Get season - returns a season on given date (start date of the booking)
    public Optional<Season> get(LocalDate bookingStartDate) {

        // Set up
        Stream <Season> seasons = getAllSeasons().stream();
        int bookStartM = bookingStartDate.getMonthValue();

        List<Season> season = seasons.filter(season1 ->
                        season1.getStartMonth() <= bookStartM
                                && season1.getStartMonth() > bookStartM)
                .collect(Collectors.toList());

        return season.size() != 1 ? Optional.empty() : Optional.of((Season) season);

    }

    // Get all seasons
    public List<Season> getAllSeasons() { return seasonRepo.findAll(); }

    // Update a season
    public Optional<Season> update(Long id, Season season) {
        season.setID(id);
        return !(seasonRepo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(season));
    }

    // Delete a season
    public void delete(Long id) { seasonRepo.deleteById(id); }

}
