package com.dualnback.data.sound;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;


public class SoundCollectionTest {

    private final KSound sound = new KSound( 11111 );

    @Before
    public void setUp( ) {

        initMocks( this );
    }

    @Test(expected = RuntimeException.class)
    public void givenInvalidListOfSoundPlayersThenSoundCollectionConstructorThrowsRuntimeException( ) {

        List<Sound> soundClips = null;

        new SoundCollection( soundClips );
    }

    @Test
    public void givenValidListOfSoundPlayersThenCanCreateInstance( ) {

        List<Sound> soundClips = new ArrayList<>();

        soundClips.add( sound );

        new SoundCollection( soundClips );
    }

    @Test
    public void givenSoundCollectionThenCanGetCountOfSoundClipsInCollection( ) {

        List<Sound> soundClips = new ArrayList<>();
        soundClips.add( sound );

        SoundCollection soundCollection = new SoundCollection( soundClips );

        assertEquals( soundClips.size(), soundCollection.getCountOfSoundClips() );
    }

    @Test
    public void givenSoundCollectionThenCanGetARandomSoundPlayer( ) {

        List<Sound> soundClips = new ArrayList<>();
        soundClips.add( sound );

        Sound soundPlayer = new SoundCollection( soundClips ).getRandomSound();

        assertNotNull( soundPlayer );
    }
}
