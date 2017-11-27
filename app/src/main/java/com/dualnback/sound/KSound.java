package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class KSound extends Sound {

    public KSound( Context context ) {
        soundResource = R.raw.k;
        mediaPlayer = MediaPlayer.create( context, R.raw.k );
    }

    public KSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
