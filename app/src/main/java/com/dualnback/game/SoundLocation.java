package com.dualnback.game;

import com.dualnback.data.location.Location;
import com.dualnback.data.sound.Sound;

public class SoundLocation {

    private final Sound sound;
    private final Location location;

    SoundLocation( Sound sound, Location location ) {
        this.sound = sound;
        this.location = location;
    }

    public Sound getSound( ) {
        return this.sound;
    }

    public Location getLocation( ) {
        return this.location;
    }
}
