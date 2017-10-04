package com.dualnback.sound;


import android.content.Context;
import android.media.MediaPlayer;

import com.dualnback.R;

public class SoundPlayer {

    private final MediaPlayer sound;

    public SoundPlayer( MediaPlayer sound ) {
        this.sound = sound;
    }

    public void play( ) {
        sound.start();
    }
}
