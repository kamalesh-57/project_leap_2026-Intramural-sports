package com.disasterrelief.sportstournament.Controller;



import com.disasterrelief.sportstournament.Entity.Fixture;
import com.disasterrelief.sportstournament.Service.FixtureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fixtures")
public class FixtureController {

    private final FixtureService fixtureService;

    public FixtureController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }

    @PostMapping("/generate")
    public List<Fixture> generateFixtures() {
        return fixtureService.generateFixtures();
    }

    @GetMapping
    public List<Fixture> getAllFixtures() {
        return fixtureService.getAllFixtures();
    }

    @GetMapping("/{id}")
    public Fixture getFixtureById(@PathVariable Long id) {
        return fixtureService.getFixtureById(id);
    }
}
