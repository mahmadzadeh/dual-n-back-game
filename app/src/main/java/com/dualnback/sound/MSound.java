package com.dualnback.sound;

import com.dualnback.R;

public class MSound extends Sound {

    public MSound( ) {
        soundRespurce = R.raw.m;
    }

    public MSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
