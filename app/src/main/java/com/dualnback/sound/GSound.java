package com.dualnback.sound;

import com.dualnback.R;

public class GSound extends Sound {

    public GSound( ) {
        soundRespurce = R.raw.g;
    }

    public GSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
