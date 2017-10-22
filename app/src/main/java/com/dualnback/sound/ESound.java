package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class ESound extends Sound {

    public ESound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.e );
    }

    public ESound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
