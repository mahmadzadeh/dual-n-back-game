package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class CSound extends Sound {

    public CSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.c );
    }

    public CSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
