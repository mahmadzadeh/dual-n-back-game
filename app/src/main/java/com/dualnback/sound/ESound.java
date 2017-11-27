package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class ESound extends Sound {

    public ESound( Context context ) {
        soundResource = R.raw.e;
        mediaPlayer = MediaPlayer.create( context, R.raw.e );
    }

    public ESound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
