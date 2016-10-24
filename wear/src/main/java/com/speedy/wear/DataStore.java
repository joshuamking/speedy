package com.speedy.wear;

import com.speedy.api.value.value.GameOptions;
import com.speedy.api.value.value.Player;

/**
 * Created by Joshua King on 10/23/16.
 */
public class DataStore {
	private static GameOptions gameOptions = new GameOptions(0, 0, 0);
	private static Player player;

	public static Player getPlayer () {
		return player;
	}

	public static void setDistance (int distance) {
		gameOptions = new GameOptions(gameOptions.getRaceType(), distance, gameOptions.getRaceMembers());
	}

	public static void setMedium (int medium) {
		gameOptions = new GameOptions(medium, gameOptions.getRaceDistance(), gameOptions.getRaceMembers());
	}

	public static void setMembers (int members) {
		gameOptions = new GameOptions(gameOptions.getRaceType(), gameOptions.getRaceDistance(), members);
	}

	public static void setName (String name) {
		player = new Player(name);
	}
}
