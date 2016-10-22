package com.speedy.ui.lobby;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.speedy.R;
import com.speedy.api.value.GameOptions;
import com.speedy.api.value.Lobby;
import com.speedy.api.value.Player;
import com.speedy.app.PrefsKeys;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LobbyActivity extends AppCompatActivity {

    public static final String KEY_USER = "intent_key_user";

    @BindView(R.id.button) Button button;
    LobbyModel model = new LobbyModel();
    Player player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onButtonClick(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(PrefsKeys.KEY_USERNAME, "");
        int distance = sharedPreferences.getInt(PrefsKeys.KEY_DISTANCE, 0);
        int medium = sharedPreferences.getInt(PrefsKeys.KEY_TYPE, 0);
        int numberOfPlayers = sharedPreferences.getInt(PrefsKeys.KEY_MEMBERS, 0);

        Player player = new Player(username);
        GameOptions options = new GameOptions(medium, distance, numberOfPlayers);

        model.searchForGame(player, options, new LobbyModel.OnRoomUpdated() {
            @Override
            public void onAddPlayer(String name) {

            }

            @Override
            public void onSearchComplete(Lobby lobby) {

            }

            @Override
            public void onFindRoom() {

            }
        });
    }
}
