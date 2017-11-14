package com.dualnback.sound;

import com.dualnback.R;

public class LSound extends Sound {

    public LSound( ) {
        soundRespurce = R.raw.l;
    }

    public LSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
