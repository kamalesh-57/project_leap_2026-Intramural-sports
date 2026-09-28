package com.disasterrelief.sportstournament.Controller;



import com.disasterrelief.sportstournament.Entity.Standing;
import com.disasterrelief.sportstournament.Service.StandingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/standings")
public class StandingController {

    private final StandingService standingService;

    public StandingController(StandingService standingService) {
        this.standingService = standingService;
    }

    @GetMapping
    public List<Standing> getStandings() {
        return standingService.getStandings();
    }

    @GetMapping("/{id}")
    public Standing getStandingById(@PathVariable Long id) {
        return standingService.getStandingById(id);
    }
}
