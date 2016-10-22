package com.speedy.api;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBase {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    public DatabaseReference get(String ref){
        return database.getReference(ref);
    }
}
