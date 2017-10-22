package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class DSound extends Sound {

    public DSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.d );
    }

    public DSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
