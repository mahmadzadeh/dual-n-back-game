package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class OSound extends Sound {

    public OSound( Context context ) {
        soundResource = R.raw.o;
        mediaPlayer = MediaPlayer.create( context, R.raw.o );
    }

    public OSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
