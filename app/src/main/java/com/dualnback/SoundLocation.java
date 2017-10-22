package com.dualnback;

import com.dualnback.location.Location;
import com.dualnback.sound.Sound;

class SoundLocation {

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
