package com.dualnback.game;


import com.dualnback.location.Location;
import com.dualnback.sound.Sound;

public class Trial {

    private final Location location;
    private final Sound sound;

    public Trial( Location location, Sound sound ) {
        this.location = location;
        this.sound = sound;
    }


    public Location getLocation( ) {
        return location;
    }

    public Sound getSound( ) {
        return sound;
    }
}
