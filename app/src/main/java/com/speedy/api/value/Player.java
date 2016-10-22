package com.speedy.api.value;

import java.io.Serializable;

public class Player implements Serializable{
    private String name;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
