package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class PSound extends Sound {

    public PSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.p );
    }

    public PSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
