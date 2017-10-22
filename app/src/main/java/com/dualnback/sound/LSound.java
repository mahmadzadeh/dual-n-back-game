package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class LSound extends Sound {

    public LSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.l );
    }

    public LSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
