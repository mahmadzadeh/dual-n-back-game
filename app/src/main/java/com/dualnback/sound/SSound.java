package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class SSound extends Sound {

    public SSound( Context context ) {
        soundResource = R.raw.s;
        mediaPlayer = MediaPlayer.create( context, R.raw.s );
    }

    public SSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
