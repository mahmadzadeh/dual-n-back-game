package com.dualnback.data.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class XSound extends Sound {

    public XSound( Context context ) {
        soundResource = R.raw.x;
        mediaPlayer = MediaPlayer.create( context, R.raw.x );
    }

    public XSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
