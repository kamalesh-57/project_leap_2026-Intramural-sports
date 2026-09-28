package com.disasterrelief.sportstournament.Repository;



import com.disasterrelief.sportstournament.Entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}