package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class FSound extends Sound {

    public FSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.f );
    }

    public FSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
