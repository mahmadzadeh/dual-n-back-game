package com.dualnback.sound;

import com.dualnback.R;

public class NSound extends Sound {

    public NSound( ) {
        soundRespurce = R.raw.n;
    }

    public NSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
