package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class ISound extends Sound {

    public ISound( Context context ) {
        soundResource = R.raw.i;
        mediaPlayer = MediaPlayer.create( context, R.raw.i );
    }

    public ISound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
