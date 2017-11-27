package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class WSound extends Sound {

    public WSound( Context context ) {
        soundResource = R.raw.v;
        mediaPlayer = MediaPlayer.create( context, R.raw.v );
    }

    public WSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
