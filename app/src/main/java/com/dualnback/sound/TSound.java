package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class TSound extends Sound {

    public TSound( Context context ) {
        soundResource = R.raw.t;
        mediaPlayer = MediaPlayer.create( context, R.raw.t );
    }

    public TSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
