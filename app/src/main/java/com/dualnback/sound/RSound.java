package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class RSound extends Sound {

    public RSound( Context context ) {
        soundResource = R.raw.r;
        mediaPlayer = MediaPlayer.create( context, R.raw.r );
    }

    public RSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
