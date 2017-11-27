package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class USound extends Sound {

    public USound( Context context ) {
        soundResource = R.raw.u;
        mediaPlayer = MediaPlayer.create( context, R.raw.u );
    }

    public USound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
