package com.dualnback.sound;

import com.dualnback.R;

public class BSound extends Sound {

    public BSound( ) {
        soundRespurce = R.raw.b;
    }

    public BSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
