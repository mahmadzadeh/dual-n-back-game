package com.dualnback.sound;

import android.content.Context;
import android.media.MediaPlayer;


public abstract class Sound {

    protected int soundRespurce;

    abstract int getSoundResource( );

    public boolean matches( Sound another ) {
        return this.getSoundResource() == another.getSoundResource();
    }

    public void playSound( Context context ) {
        MediaPlayer mediaPlayer = MediaPlayer.create( context, getSoundResource() );
        mediaPlayer.start();
    }
}
