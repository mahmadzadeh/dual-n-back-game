package com.dualnback.sound;

import com.dualnback.R;

public class ASound extends Sound {

    public ASound( ) {
        soundRespurce = R.raw.a;
    }

    public ASound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
