package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class TSound extends Sound {

    public TSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.t );
    }

    public TSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
