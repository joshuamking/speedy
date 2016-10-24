package com.speedy.api.value.value;

import me.denley.courier.Deliverable;

@Deliverable
public class Player {
	private String name;

	public Player () {
	}

	public Player (String name) {
		this.name = name;
	}

	public String getName () {
		return name;
	}
}
