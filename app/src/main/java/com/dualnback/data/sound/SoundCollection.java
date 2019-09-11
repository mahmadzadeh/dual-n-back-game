package com.dualnback.data.sound;

import com.dualnback.data.util.random.RandomNumberGenerator;
import com.dualnback.util.IntegerRange;

import java.util.List;

public class SoundCollection {

    private final List<Sound> soundClips;

    public SoundCollection( List<Sound> soundClips ) {

        if ( soundClips.isEmpty() ) {
            throw new IllegalArgumentException( "Unable to create sound collection. Need a non-empty list of sound players " );
        }

        this.soundClips = soundClips;
    }

    public int getCountOfSoundClips( ) {
        return soundClips.size();
    }


    public Sound getRandomSound( ) {
        int index = RandomNumberGenerator.next( new IntegerRange( 0, soundClips.size() - 1 ) );

        return soundClips.get( index );
    }
}
