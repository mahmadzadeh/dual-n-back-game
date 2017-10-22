package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class HSound extends Sound {

    public HSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.h );
    }

    public HSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
