package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class USound extends Sound {

    public USound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.u );
    }

    public USound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
