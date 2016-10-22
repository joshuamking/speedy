package com.speedy.ui.race;

import android.location.Location;

import com.google.firebase.database.DatabaseReference;
import com.speedy.api.FireBase;
import com.speedy.api.value.RaceCoordinates;

public class LocationRaceModel {
    private FireBase fireBase;

    public LocationRaceModel() {
        this.fireBase = new FireBase();
    }

    public void updateLocation(Location location, int user){
        DatabaseReference reference = fireBase.get("games/0/positions/" + user + "/position_history");
        RaceCoordinates coordinates = new RaceCoordinates(location.getLatitude(), location.getLongitude());

        reference.push().setValue(coordinates);
    }
}
