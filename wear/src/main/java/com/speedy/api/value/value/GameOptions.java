package com.speedy.api.value.value;

import me.denley.courier.Deliverable;

@Deliverable
public class GameOptions {
	private int raceDistance;
	private int raceMembers;
	private int raceType;

	public GameOptions (int raceType, int raceDistance, int raceMembers) {
		this.raceType = raceType;
		this.raceDistance = raceDistance;
		this.raceMembers = raceMembers;
	}

	public GameOptions () {
	}

	public int getRaceType () {
		return raceType;
	}

	public int getRaceDistance () {
		return raceDistance;
	}

	public int getRaceMembers () {
		return raceMembers;
	}
}
