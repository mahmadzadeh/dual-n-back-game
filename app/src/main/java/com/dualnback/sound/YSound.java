package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class YSound extends Sound {

    public YSound( Context context ) {
        soundResource = R.raw.y;
        mediaPlayer = MediaPlayer.create( context, R.raw.y );
    }

    public YSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
