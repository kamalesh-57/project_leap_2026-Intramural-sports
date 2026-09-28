package com.disasterrelief.sportstournament.Repository;

import com.disasterrelief.sportstournament.Entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {
}