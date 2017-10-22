package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class ISound extends Sound {

    public ISound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.i );
    }

    public ISound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
