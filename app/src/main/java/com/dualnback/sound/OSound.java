package com.dualnback.sound;

import com.dualnback.R;

public class OSound extends Sound {

    public OSound( ) {
        soundRespurce = R.raw.o;
    }

    public OSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
