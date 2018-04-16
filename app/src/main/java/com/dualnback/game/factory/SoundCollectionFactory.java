package com.dualnback.game.factory;

import android.content.Context;

import com.dualnback.sound.BSound;
import com.dualnback.sound.FSound;
import com.dualnback.sound.GSound;
import com.dualnback.sound.HSound;
import com.dualnback.sound.JSound;
import com.dualnback.sound.KSound;
import com.dualnback.sound.LSound;
import com.dualnback.sound.MSound;
import com.dualnback.sound.QSound;
import com.dualnback.sound.SSound;
import com.dualnback.sound.Sound;
import com.dualnback.sound.TSound;
import com.dualnback.sound.VSound;
import com.dualnback.sound.XSound;

import java.util.ArrayList;
import java.util.List;


public class SoundCollectionFactory {
    private static List<Sound> soundClips = new ArrayList<>();

    public static List<Sound> instance( Context context ) {
        if ( !soundClips.isEmpty() ) {
            return soundClips;
        }

        soundClips.add( new BSound( context ) );
        soundClips.add( new FSound( context ) );
        soundClips.add( new GSound( context ) );
        soundClips.add( new HSound( context ) );
        soundClips.add( new JSound( context ) );
        soundClips.add( new KSound( context ) );
        soundClips.add( new LSound( context ) );
        soundClips.add( new MSound( context ) );
        soundClips.add( new QSound( context ) );
        soundClips.add( new SSound( context ) );
        soundClips.add( new TSound( context ) );
        soundClips.add( new VSound( context ) );
        soundClips.add( new XSound( context ) );

        return soundClips;
    }

}
