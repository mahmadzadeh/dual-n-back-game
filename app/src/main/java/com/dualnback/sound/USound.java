package com.dualnback.sound;

import com.dualnback.R;

public class USound extends Sound {

    public USound( ) {
        soundRespurce = R.raw.u;
    }

    public USound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
