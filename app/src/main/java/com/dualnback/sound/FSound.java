package com.dualnback.sound;

import com.dualnback.R;

public class FSound extends Sound {

    public FSound( ) {
        soundRespurce = R.raw.f;
    }

    public FSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
