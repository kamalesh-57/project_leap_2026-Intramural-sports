package com.disasterrelief.sportstournament.Service;


import com.disasterrelief.sportstournament.Entity.Fixture;
import com.disasterrelief.sportstournament.Entity.Team;
import com.disasterrelief.sportstournament.Repository.FixtureRepository;
import com.disasterrelief.sportstournament.Repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FixtureService {

    private final FixtureRepository fixtureRepository;
    private final TeamRepository teamRepository;

    public FixtureService(FixtureRepository fixtureRepository,
                          TeamRepository teamRepository) {
        this.fixtureRepository = fixtureRepository;
        this.teamRepository = teamRepository;
    }

    // Generate round-robin fixtures
    public List<Fixture> generateFixtures() {

        List<Team> teams = teamRepository.findAll();
        List<Fixture> fixtures = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {

            for (int j = i + 1; j < teams.size(); j++) {

                Team homeTeam = teams.get(i);
                Team awayTeam = teams.get(j);

                Fixture fixture = new Fixture(
                        homeTeam,
                        awayTeam,
                        "Not Scheduled",
                        "Scheduled"
                );

                fixtures.add(fixture);
            }
        }

        return fixtureRepository.saveAll(fixtures);
    }

    // Get all fixtures
    public List<Fixture> getAllFixtures() {
        return fixtureRepository.findAll();
    }

    // Get fixture by ID
    public Fixture getFixtureById(Long id) {
        return fixtureRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Fixture not found with ID: " + id));
    }
}
