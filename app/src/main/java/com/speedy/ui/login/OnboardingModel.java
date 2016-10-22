package com.speedy.ui.login;


import com.google.firebase.database.DatabaseReference;
import com.speedy.api.FireBase;
import com.speedy.api.value.Player;

public class OnboardingModel {
    private FireBase fireBase;

    public OnboardingModel() {
        this.fireBase = new FireBase();
    }

    public void savePlayer(String username){
        DatabaseReference ref = fireBase.get("players");
        Player player = new Player(username);
        ref.push().setValue(player);
    }
}
