package com.disasterrelief.sportstournament.Service;



import com.disasterrelief.sportstournament.Entity.Standing;
import com.disasterrelief.sportstournament.Repository.StandingRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StandingService {

    private final StandingRepository standingRepository;

    public StandingService(StandingRepository standingRepository) {
        this.standingRepository = standingRepository;
    }

    // Get standings sorted by points
    public List<Standing> getStandings() {

        List<Standing> standings = standingRepository.findAll();

        standings.sort(
                Comparator.comparing(Standing::getPoints).reversed()
        );

        return standings;
    }

    // Get standing by ID
    public Standing getStandingById(Long id) {

        return standingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Standing not found with ID: " + id
                        )
                );
    }
}
