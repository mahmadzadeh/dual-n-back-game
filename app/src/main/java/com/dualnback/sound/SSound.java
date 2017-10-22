package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class SSound extends Sound {

    public SSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.s );
    }

    public SSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
