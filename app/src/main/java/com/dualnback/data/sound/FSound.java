package com.dualnback.data.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class FSound extends Sound {

    public FSound( Context context ) {
        soundResource = R.raw.f;
        mediaPlayer = MediaPlayer.create( context, R.raw.f );
    }

    public FSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
