package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class ZSound extends Sound {

    public ZSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.z );
    }

    public ZSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
