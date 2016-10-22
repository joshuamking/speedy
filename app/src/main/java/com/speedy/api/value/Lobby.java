package com.speedy.api.value;

public class Lobby {
    private int raceType;
    private int raceDistance;
    private int raceMembers;

    public Lobby() {
    }

    public Lobby(int raceType, int raceDistance, int raceMembers) {
        this.raceType = raceType;
        this.raceDistance = raceDistance;
        this.raceMembers = raceMembers;
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
}
