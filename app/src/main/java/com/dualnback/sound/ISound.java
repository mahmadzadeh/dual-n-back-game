package com.dualnback.sound;

import com.dualnback.R;

public class ISound extends Sound {

    public ISound( ) {
        soundRespurce = R.raw.i;
    }

    public ISound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
