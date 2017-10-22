package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class QSound extends Sound {

    public QSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.q );
    }

    public QSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
