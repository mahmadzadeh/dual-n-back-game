package com.dualnback.sound;

import android.media.MediaPlayer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;


public class SoundCollectionTest {

    @Mock
    private MediaPlayer mockMediaPlayer;


    @Before
    public void setUp( ) {

        initMocks( this );
    }

    @Test(expected = RuntimeException.class)
    public void givenInvalidListOfSoundPlayersThenSoundCollectionConstructorThrowsRuntimeException( ) {

        List<SoundPlayer> soundClips = null;

        new SoundCollection( soundClips );
    }

    @Test
    public void givenValidListOfSoundPlayersThenCanCreateInstance( ) {

        List<SoundPlayer> soundClips = new ArrayList<>();
        soundClips.add( new SoundPlayer( mockMediaPlayer ) );

        new SoundCollection( soundClips );
    }

    @Test
    public void givenSoundCollectionThenCanGetCountOfSoundClipsInCollection( ) {

        List<SoundPlayer> soundClips = new ArrayList<>();
        soundClips.add( new SoundPlayer( mockMediaPlayer ) );

        SoundCollection soundCollection = new SoundCollection( soundClips );

        assertEquals( soundClips.size(), soundCollection.getCountOfSoundClips() );
    }

    @Test
    public void givenSoundCollectionThenCanGetARandomSoundPlayer( ) {

        List<SoundPlayer> soundClips = new ArrayList<>();
        soundClips.add( new SoundPlayer( mockMediaPlayer ) );

        SoundPlayer soundPlayer = new SoundCollection( soundClips ).randomSoundPlayer();

        assertNotNull( soundPlayer );
    }
}
