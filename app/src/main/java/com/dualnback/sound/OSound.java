package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class OSound extends Sound {

    public OSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.o );
    }

    public OSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
