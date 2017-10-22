package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class ASound extends Sound {

    public ASound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.a );
    }

    public ASound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
