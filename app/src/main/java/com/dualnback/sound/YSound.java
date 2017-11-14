package com.dualnback.sound;

import com.dualnback.R;

public class YSound extends Sound {

    public YSound( ) {
        soundRespurce = R.raw.y;
    }

    public YSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
