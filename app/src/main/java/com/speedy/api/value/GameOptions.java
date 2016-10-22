package com.speedy.api.value;


public class GameOptions {
    private int raceType;
    private int raceDistance;
    private int raceMembers;

    public GameOptions(int raceType, int raceDistance, int raceMembers) {
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
