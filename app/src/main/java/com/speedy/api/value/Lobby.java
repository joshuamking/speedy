package com.speedy.api.value;

import java.util.List;

public class Lobby {
    private int raceType;
    private int raceDistance;
    private int raceMembers;
    private List<String> players;

    public Lobby() {
    }

    public Lobby(int raceType, int raceDistance, int raceMembers, List<String> players) {
        this.raceType = raceType;
        this.raceDistance = raceDistance;
        this.raceMembers = raceMembers;
        this.players = players;
    }

    public int getRaceType() {
        return raceType;
    }

    public int getRaceDistance() {
        return raceDistance;
    }

    public int getRaceMembers() {
        return raceMembers;
    }

    public List<String> getPlayers() {
        return players;
    }
}
