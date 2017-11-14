package com.dualnback.sound;

import com.dualnback.R;

public class VSound extends Sound {

    public VSound( ) {
        soundRespurce = R.raw.v;
    }

    public VSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
