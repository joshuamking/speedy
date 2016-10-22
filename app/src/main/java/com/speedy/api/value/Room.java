package com.speedy.api.value;

import java.util.List;

public class Room {
    private int raceType;
    private int raceDistance;
    private int raceMembers;
    private List<Integer> players;

    public Room() {
    }

    public Room(int raceType, int raceDistance, int raceMembers, List<Integer> players) {
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

    public List<Integer> getPlayers() {
        return players;
    }
}
