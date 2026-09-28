package com.disasterrelief.sportstournament.Repository;


import com.disasterrelief.sportstournament.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}