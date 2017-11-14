package com.dualnback.sound;

import com.dualnback.R;

public class CSound extends Sound {

    public CSound( ) {
        soundRespurce = R.raw.c;
    }

    public CSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
