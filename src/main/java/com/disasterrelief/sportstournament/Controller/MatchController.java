package com.disasterrelief.sportstournament.Controller;
import com.disasterrelief.sportstournament.Entity.Match;
import com.disasterrelief.sportstournament.Service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/result")
    public Match recordResult(
            @RequestParam Long fixtureId,
            @RequestParam int homeScore,
            @RequestParam int awayScore) {

        return matchService.recordResult(
                fixtureId,
                homeScore,
                awayScore
        );
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id);
    }
}
