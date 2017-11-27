package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class DSound extends Sound {

    public DSound( Context context ) {
        soundResource = R.raw.d;
        mediaPlayer = MediaPlayer.create( context, R.raw.d );
    }

    public DSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
