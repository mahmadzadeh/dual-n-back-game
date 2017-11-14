package com.dualnback.sound;

import com.dualnback.R;

public class DSound extends Sound {

    public DSound( ) {
        soundRespurce = R.raw.d;
    }

    public DSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
