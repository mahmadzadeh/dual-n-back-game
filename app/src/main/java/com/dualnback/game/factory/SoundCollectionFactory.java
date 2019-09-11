package com.dualnback.game.factory;

import android.content.Context;

import com.dualnback.data.sound.BSound;
import com.dualnback.data.sound.FSound;
import com.dualnback.data.sound.GSound;
import com.dualnback.data.sound.HSound;
import com.dualnback.data.sound.JSound;
import com.dualnback.data.sound.KSound;
import com.dualnback.data.sound.LSound;
import com.dualnback.data.sound.MSound;
import com.dualnback.data.sound.QSound;
import com.dualnback.data.sound.SSound;
import com.dualnback.data.sound.Sound;
import com.dualnback.data.sound.TSound;
import com.dualnback.data.sound.VSound;
import com.dualnback.data.sound.XSound;

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
