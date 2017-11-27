package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class GSound extends Sound {

    public GSound( Context context ) {
        soundResource = R.raw.g;
        mediaPlayer = MediaPlayer.create( context, R.raw.g );
    }

    public GSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
