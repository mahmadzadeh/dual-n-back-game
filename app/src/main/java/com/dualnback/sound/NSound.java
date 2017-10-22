package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class NSound extends Sound {

    public NSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.n );
    }

    public NSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
