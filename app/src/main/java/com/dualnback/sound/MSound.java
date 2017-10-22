package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class MSound extends Sound {

    public MSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.m );
    }

    public MSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
