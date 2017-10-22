package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class VSound extends Sound {

    public VSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.v );
    }

    public VSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
