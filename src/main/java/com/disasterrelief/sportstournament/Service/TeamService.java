package com.disasterrelief.sportstournament.Service;



import com.disasterrelief.sportstournament.Entity.Team;
import com.disasterrelief.sportstournament.Repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Add a new team
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    // Get all teams
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Get team by ID
    public Team getTeamById(Long id) {
        Optional<Team> team = teamRepository.findById(id);

        if (team.isPresent()) {
            return team.get();
        } else {
            throw new RuntimeException("Team not found with ID: " + id);
        }
    }

    // Update team
    public Team updateTeam(Long id, Team updatedTeam) {
        Team team = getTeamById(id);

        team.setName(updatedTeam.getName());
        team.setDepartment(updatedTeam.getDepartment());

        return teamRepository.save(team);
    }

    // Delete team
    public void deleteTeam(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new RuntimeException("Team not found with ID: " + id);
        }

        teamRepository.deleteById(id);
    }
}
