package com.dualnback.sound;

import com.dualnback.R;

public class WSound extends Sound {

    public WSound( ) {
        soundRespurce = R.raw.w;
    }

    public WSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
