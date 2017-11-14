package com.dualnback.sound;

import com.dualnback.R;

public class JSound extends Sound {

    public JSound( ) {
        soundRespurce = R.raw.j;
    }

    public JSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
