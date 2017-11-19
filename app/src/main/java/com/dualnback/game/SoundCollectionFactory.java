package com.dualnback.game;

import com.dualnback.sound.*;

import java.util.ArrayList;
import java.util.List;


public class SoundCollectionFactory {

    public static List<Sound> instance( ) {
        List<Sound> soundClips = new ArrayList<>();

        soundClips.add( new ASound() );
        soundClips.add( new BSound() );
        soundClips.add( new CSound() );
        soundClips.add( new ESound() );
        soundClips.add( new FSound() );
        soundClips.add( new GSound() );
        soundClips.add( new HSound() );
        soundClips.add( new ISound() );
        soundClips.add( new JSound() );
        soundClips.add( new KSound() );
        soundClips.add( new LSound() );
        soundClips.add( new MSound() );
        soundClips.add( new NSound() );
        soundClips.add( new OSound() );
        soundClips.add( new PSound() );
        soundClips.add( new QSound() );
        soundClips.add( new RSound() );
        soundClips.add( new SSound() );
        soundClips.add( new TSound() );
        soundClips.add( new USound() );
        soundClips.add( new VSound() );
        soundClips.add( new WSound() );
        soundClips.add( new XSound() );
        soundClips.add( new YSound() );
        soundClips.add( new ZSound() );

        return soundClips;
    }

}
