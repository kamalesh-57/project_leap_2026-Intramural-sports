package com.disasterrelief.sportstournament.Service;
import com.disasterrelief.sportstournament.Entity.Fixture;
import com.disasterrelief.sportstournament.Entity.Match;
import com.disasterrelief.sportstournament.Entity.Standing;
import com.disasterrelief.sportstournament.Repository.FixtureRepository;
import com.disasterrelief.sportstournament.Repository.MatchRepository;
import com.disasterrelief.sportstournament.Repository.StandingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final FixtureRepository fixtureRepository;
    private final StandingRepository standingRepository;

    public MatchService(MatchRepository matchRepository,
                        FixtureRepository fixtureRepository,
                        StandingRepository standingRepository) {
        this.matchRepository = matchRepository;
        this.fixtureRepository = fixtureRepository;
        this.standingRepository = standingRepository;
    }

    // Record match result
    public Match recordResult(Long fixtureId, int homeScore, int awayScore) {

        if (homeScore < 0 || awayScore < 0) {
            throw new RuntimeException("Score cannot be negative");
        }

        Fixture fixture = fixtureRepository.findById(fixtureId)
                .orElseThrow(() ->
                        new RuntimeException("Fixture not found with ID: " + fixtureId));

        Match match = new Match(fixture, homeScore, awayScore);

        updateStandings(
                fixture,
                homeScore,
                awayScore
        );

        fixture.setStatus("Completed");
        fixtureRepository.save(fixture);

        return matchRepository.save(match);
    }

    // Update standings
    private void updateStandings(Fixture fixture,
                                 int homeScore,
                                 int awayScore) {

        Standing homeStanding = standingRepository.findAll()
                .stream()
                .filter(s -> s.getTeam().getId()
                        .equals(fixture.getHomeTeam().getId()))
                .findFirst()
                .orElseGet(() ->
                        standingRepository.save(
                                new Standing(fixture.getHomeTeam())
                        ));

        Standing awayStanding = standingRepository.findAll()
                .stream()
                .filter(s -> s.getTeam().getId()
                        .equals(fixture.getAwayTeam().getId()))
                .findFirst()
                .orElseGet(() ->
                        standingRepository.save(
                                new Standing(fixture.getAwayTeam())
                        ));

        homeStanding.setPlayed(homeStanding.getPlayed() + 1);
        awayStanding.setPlayed(awayStanding.getPlayed() + 1);

        if (homeScore > awayScore) {

            homeStanding.setWins(homeStanding.getWins() + 1);
            homeStanding.setPoints(homeStanding.getPoints() + 3);

            awayStanding.setLosses(awayStanding.getLosses() + 1);

        } else if (homeScore < awayScore) {

            awayStanding.setWins(awayStanding.getWins() + 1);
            awayStanding.setPoints(awayStanding.getPoints() + 3);

            homeStanding.setLosses(homeStanding.getLosses() + 1);

        } else {

            homeStanding.setDraws(homeStanding.getDraws() + 1);
            awayStanding.setDraws(awayStanding.getDraws() + 1);

            homeStanding.setPoints(homeStanding.getPoints() + 1);
            awayStanding.setPoints(awayStanding.getPoints() + 1);
        }

        standingRepository.save(homeStanding);
        standingRepository.save(awayStanding);
    }

    // Get all matches
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    // Get match by ID
    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Match not found with ID: " + id));
    }
}
