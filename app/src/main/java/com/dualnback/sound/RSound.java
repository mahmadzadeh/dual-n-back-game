package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class RSound extends Sound {

    public RSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.r );
    }

    public RSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
