package com.dualnback.sound;

import com.dualnback.R;

public class QSound extends Sound {

    public QSound( ) {
        soundRespurce = R.raw.q;
    }

    public QSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
