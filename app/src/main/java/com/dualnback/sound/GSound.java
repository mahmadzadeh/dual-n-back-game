package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class GSound extends Sound {

    public GSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.g );
    }

    public GSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
