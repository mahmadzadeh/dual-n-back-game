package com.dualnback.sound;

import com.dualnback.IntegerRange;
import com.dualnback.RandomNumberGenerator;

import java.util.List;

public class SoundCollection {

    private final List<SoundPlayer> soundClips;

    public SoundCollection( List<SoundPlayer> soundClips ) {

        if ( soundClips.isEmpty() ) {
            throw new IllegalArgumentException( "Unable to create sound collection. Need a non-empty list of sound players " );
        }

        this.soundClips = soundClips;
    }

    public int getCountOfSoundClips( ) {
        return soundClips.size();
    }


    public SoundPlayer randomSoundPlayer( ) {
        int index = RandomNumberGenerator.next( new IntegerRange( 0, soundClips.size() - 1 ) );

        return soundClips.get( index );
    }
}
