package com.dualnback.game.factory;

import android.content.Context;

import com.dualnback.sound.ASound;
import com.dualnback.sound.BSound;
import com.dualnback.sound.CSound;
import com.dualnback.sound.DSound;
import com.dualnback.sound.ESound;
import com.dualnback.sound.FSound;
import com.dualnback.sound.GSound;
import com.dualnback.sound.HSound;
import com.dualnback.sound.ISound;
import com.dualnback.sound.JSound;
import com.dualnback.sound.KSound;
import com.dualnback.sound.LSound;
import com.dualnback.sound.MSound;
import com.dualnback.sound.NSound;
import com.dualnback.sound.OSound;
import com.dualnback.sound.PSound;
import com.dualnback.sound.QSound;
import com.dualnback.sound.RSound;
import com.dualnback.sound.SSound;
import com.dualnback.sound.Sound;
import com.dualnback.sound.TSound;
import com.dualnback.sound.USound;
import com.dualnback.sound.VSound;
import com.dualnback.sound.WSound;
import com.dualnback.sound.XSound;
import com.dualnback.sound.YSound;
import com.dualnback.sound.ZSound;

import java.util.ArrayList;
import java.util.List;


public class SoundCollectionFactory {
    private static List<Sound> soundClips = new ArrayList<>();

    public static List<Sound> instance( Context context ) {
        if ( !soundClips.isEmpty() ) {
            return soundClips;
        }

        soundClips.add( new ASound( context ) );
        soundClips.add( new BSound( context ) );
        soundClips.add( new CSound( context ) );
        soundClips.add( new DSound( context ) );
        soundClips.add( new ESound( context ) );
        soundClips.add( new FSound( context ) );
        soundClips.add( new GSound( context ) );
        soundClips.add( new HSound( context ) );
        soundClips.add( new ISound( context ) );
        soundClips.add( new JSound( context ) );
        soundClips.add( new KSound( context ) );
        soundClips.add( new LSound( context ) );
        soundClips.add( new MSound( context ) );
        soundClips.add( new NSound( context ) );
        soundClips.add( new OSound( context ) );
        soundClips.add( new PSound( context ) );
        soundClips.add( new QSound( context ) );
        soundClips.add( new RSound( context ) );
        soundClips.add( new SSound( context ) );
        soundClips.add( new TSound( context ) );
        soundClips.add( new USound( context ) );
        soundClips.add( new VSound( context ) );
        soundClips.add( new WSound( context ) );
        soundClips.add( new XSound( context ) );
        soundClips.add( new YSound( context ) );
        soundClips.add( new ZSound( context ) );

        return soundClips;
    }

}
