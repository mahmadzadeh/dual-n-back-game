package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class YSound extends Sound {

    public YSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.y );
    }

    public YSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
