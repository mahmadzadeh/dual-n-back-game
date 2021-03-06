package com.dualnback.data.sound;

import android.media.MediaPlayer;

import java.util.Objects;


public abstract class Sound {

    protected int soundResource;
    protected MediaPlayer mediaPlayer;

    abstract int getSoundResource( );

    public void playSound( ) {
        mediaPlayer.start();
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Sound sound = ( Sound ) o;
        return getSoundResource() == sound.getSoundResource();
    }

    @Override
    public int hashCode( ) {
        return Objects.hash( getSoundResource() );
    }

    @Override
    public String toString( ) {
        return "Sound{ resourceId=" + getSoundResource() +
                ", class =" + this.getClass().getSimpleName() +
                '}';
    }
}
