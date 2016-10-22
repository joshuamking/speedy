package com.speedy.ui.lobby;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.speedy.api.value.GameOptions;
import com.speedy.api.value.Lobby;
import com.speedy.api.value.Player;

public class LobbyModel {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static boolean found = false;

    public void searchForGame(final Player player, final String playerId, final GameOptions options, OnRoomUpdated updateListener){
        final DatabaseReference ref = database.getReference("lobby");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Lobby lobby = postSnapshot.getValue(Lobby.class);
                    long players = postSnapshot.child("players").getChildrenCount();

                    if(players == lobby.getRaceMembers() ||
                            lobby.getRaceMembers() != options.getRaceMembers() ||
                            lobby.getRaceDistance() != options.getRaceDistance() ||
                            lobby.getRaceType() != options.getRaceType()){
                        continue;
                    }

                    found = true;
                    postSnapshot.child("players/" + (players - 1)).getRef().setValue(playerId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(found) return;
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Lobby lobby = dataSnapshot.getValue(Lobby.class);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface OnRoomUpdated{
        void onAddPlayer(String name);
        void onSearchComplete(Lobby lobby);
        void onFindRoom();
    }
}
