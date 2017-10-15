package com.dualnback;

import com.dualnback.location.Location;
import com.dualnback.sound.SoundPlayer;

class SoundLocation {

    private final SoundPlayer sound;
    private final Location location;

    SoundLocation( SoundPlayer sound, Location location ) {
        this.sound = sound;
        this.location = location;
    }

    public SoundPlayer getSound( ) {
        return this.sound;
    }

    public Location getLocation( ) {
        return this.location;
    }
}
