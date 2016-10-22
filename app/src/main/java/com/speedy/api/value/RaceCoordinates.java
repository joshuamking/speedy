package com.speedy.api.value;

public class RaceCoordinates {
    private double latitude;
    private double longitude;

    public RaceCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
