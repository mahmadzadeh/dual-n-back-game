package com.dualnback.sound;


import android.media.MediaPlayer;

public class SoundPlayer {

    private final MediaPlayer sound;

    public SoundPlayer( MediaPlayer sound ) {
        this.sound = sound;
    }

    public void play( ) {
        sound.start();
    }
}
