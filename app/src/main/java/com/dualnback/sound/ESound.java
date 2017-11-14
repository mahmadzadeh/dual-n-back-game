package com.dualnback.sound;

import com.dualnback.R;

public class ESound extends Sound {

    public ESound( ) {
        soundRespurce = R.raw.e;
    }

    public ESound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
