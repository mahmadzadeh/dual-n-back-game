package com.dualnback.sound;

import com.dualnback.R;

public class PSound extends Sound {

    public PSound( ) {
        soundRespurce = R.raw.p;
    }

    public PSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
