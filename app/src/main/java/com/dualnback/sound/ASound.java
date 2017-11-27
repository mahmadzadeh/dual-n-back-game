package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class ASound extends Sound {

    public ASound( Context context ) {
        soundResource = R.raw.a;
        mediaPlayer = MediaPlayer.create( context, R.raw.a );
    }

    public ASound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
