package com.dualnback.data.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class MSound extends Sound {

    public MSound( Context context ) {
        soundResource = R.raw.m;
        mediaPlayer = MediaPlayer.create( context, R.raw.m );
    }

    public MSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
