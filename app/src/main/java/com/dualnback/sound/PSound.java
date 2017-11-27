package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class PSound extends Sound {

    public PSound( Context context ) {
        soundResource = R.raw.p;
        mediaPlayer = MediaPlayer.create( context, R.raw.p );
    }

    public PSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
