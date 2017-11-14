package com.dualnback.sound;

import com.dualnback.R;

public class KSound extends Sound {

    public KSound( ) {
        soundRespurce = R.raw.k;
    }

    public KSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
