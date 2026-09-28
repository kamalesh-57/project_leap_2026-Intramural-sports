package com.disasterrelief.sportstournament.Repository;



import com.disasterrelief.sportstournament.Entity.Standing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandingRepository extends JpaRepository<Standing, Long> {
}
