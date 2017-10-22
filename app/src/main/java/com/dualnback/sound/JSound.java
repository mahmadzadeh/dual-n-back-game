package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class JSound extends Sound {

    public JSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.j );
    }

    public JSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
