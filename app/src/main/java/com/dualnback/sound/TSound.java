package com.dualnback.sound;

import com.dualnback.R;

public class TSound extends Sound {

    public TSound( ) {
        soundRespurce = R.raw.t;
    }

    public TSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
