package com.dualnback.sound;

import com.dualnback.R;

public class SSound extends Sound {

    public SSound( ) {
        soundRespurce = R.raw.s;
    }

    public SSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
