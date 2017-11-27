package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class ZSound extends Sound {

    public ZSound( Context context ) {
        soundResource = R.raw.z;
        mediaPlayer = MediaPlayer.create( context, R.raw.z );
    }

    public ZSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
