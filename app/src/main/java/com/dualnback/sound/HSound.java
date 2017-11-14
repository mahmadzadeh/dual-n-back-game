package com.dualnback.sound;

import com.dualnback.R;

public class HSound extends Sound {

    public HSound( ) {
        soundRespurce = R.raw.h;
    }

    public HSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
