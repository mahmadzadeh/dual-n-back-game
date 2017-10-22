package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class KSound extends Sound {

    public KSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.k );
    }

    public KSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
