package com.disasterrelief.sportstournament.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fixture_id")
    private Fixture fixture;

    private int homeScore;

    private int awayScore;

    public Match() {
    }

    public Match(Fixture fixture, int homeScore, int awayScore) {
        this.fixture = fixture;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public Long getId() {
        return id;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }
}
