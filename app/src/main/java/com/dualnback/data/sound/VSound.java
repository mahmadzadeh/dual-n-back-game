package com.dualnback.data.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class VSound extends Sound {

    public VSound( Context context ) {
        soundResource = R.raw.v;
        mediaPlayer = MediaPlayer.create( context, R.raw.v );
    }

    public VSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
