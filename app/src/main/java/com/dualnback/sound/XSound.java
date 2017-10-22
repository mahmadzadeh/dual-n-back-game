package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class XSound extends Sound {

    public XSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.x );
    }

    public XSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
