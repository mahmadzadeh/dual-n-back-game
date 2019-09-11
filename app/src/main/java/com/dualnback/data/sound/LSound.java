package com.dualnback.data.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class LSound extends Sound {

    public LSound( Context context ) {
        soundResource = R.raw.l;
        mediaPlayer = MediaPlayer.create( context, R.raw.l );
    }

    public LSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
