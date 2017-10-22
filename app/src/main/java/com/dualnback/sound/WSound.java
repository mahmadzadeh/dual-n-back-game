package com.dualnback.sound;

import android.content.res.Resources;

import com.dualnback.R;

public class WSound extends Sound {

    public WSound( ) {
        soundRespurce = Resources.getSystem().getInteger( R.raw.w );
    }

    public WSound( int resource ) {
        soundRespurce = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundRespurce;
    }
}
