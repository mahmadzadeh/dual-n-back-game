package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class CSound extends Sound {

    public CSound( Context context ) {
        soundResource = R.raw.c;
        mediaPlayer = MediaPlayer.create( context, R.raw.c );
    }

    public CSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
