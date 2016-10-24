package com.speedy.app;

import android.content.Intent;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.WearableListenerService;
import com.speedy.api.value.GameOptions;
import com.speedy.api.value.Player;
import com.speedy.ui.race.RaceActivity;
import me.denley.courier.Courier;
import me.denley.courier.Packager;

/**
 * Created by Joshua King on 10/23/16.
 */
public class DataLayerListenerService extends WearableListenerService {
	private static final String DATA_ITEM_RECEIVED_PATH = "/data-item-received";
	private static final String GAME_OPTIONS_PATH       = "/game-options";
	private static final String PLAYER_PATH             = "/player";
	private static final String TAG                     = "DataLayerSample";
	public static GameOptions gameOptions;
	public static Player      player;

	@Override
	public void onCreate () {
		super.onCreate();

		Courier.startReceiving(this);
	}

	@Override
	public void onDataChanged (DataEventBuffer dataEvents) {
		for (DataEvent event : dataEvents) {
			if (event.getDataItem().getUri().getPath().equals(GAME_OPTIONS_PATH)) {
				gameOptions = Packager.unpack(this, event.getDataItem(), GameOptions.class);
				if (gameOptions != null && player != null) { startActivity(new Intent(getApplicationContext(), RaceActivity.class)); }
			}
			else if (event.getDataItem().getUri().getPath().equals(GAME_OPTIONS_PATH)) {
				player = Packager.unpack(this, event.getDataItem(), Player.class);
				if (gameOptions != null && player != null) { startActivity(new Intent(getApplicationContext(), RaceActivity.class)); }
			}
		}
	}
}
