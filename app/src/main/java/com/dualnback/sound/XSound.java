package com.dualnback.sound;

import com.dualnback.R;

public class XSound extends Sound {

    public XSound( ) {
        soundRespurce = R.raw.x;
    }

    public XSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
