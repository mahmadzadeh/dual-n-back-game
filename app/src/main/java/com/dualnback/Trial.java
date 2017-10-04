package com.dualnback;


import com.dualnback.location.Location;
import com.dualnback.sound.SoundPlayer;

public class Trial {

    private final Location location;

    private final SoundPlayer sound;

    public Trial( Location location, SoundPlayer sound ) {
        this.location = location;
        this.sound = sound;
    }

    public Location getLocation( ) {
        return location;
    }

    public SoundPlayer getSound( ) {
        return sound;
    }



}
