package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class NSound extends Sound {

    public NSound( Context context ) {
        soundResource = R.raw.n;
        mediaPlayer = MediaPlayer.create( context, R.raw.n );
    }

    public NSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
