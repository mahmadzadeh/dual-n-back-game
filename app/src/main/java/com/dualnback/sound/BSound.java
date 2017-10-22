package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class BSound extends Sound {

    public BSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.b);
    }

    public BSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
