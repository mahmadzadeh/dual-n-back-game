package com.dualnback.sound;

import com.dualnback.R;

public class RSound extends Sound {

    public RSound( ) {
        soundRespurce = R.raw.r;
    }

    public RSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
