package com.dualnback.sound;

import com.dualnback.R;

public class ZSound extends Sound {

    public ZSound( ) {
        soundRespurce = R.raw.z;
    }

    public ZSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
