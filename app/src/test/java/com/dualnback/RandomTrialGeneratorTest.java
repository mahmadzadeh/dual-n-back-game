package com.dualnback;

import android.media.MediaPlayer;

import com.dualnback.location.Location;
import com.dualnback.location.LocationCollection;
import com.dualnback.random.RandomTrialGenerator;
import com.dualnback.sound.SoundCollection;
import com.dualnback.sound.SoundPlayer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RandomTrialGeneratorTest {

    @Mock
    private LocationCollection mockLocationCollection;

    @Mock
    private SoundCollection mockSoundCollection;

    @Mock
    private MediaPlayer mockMediaPlayer;

    @Before
    public void setUp( ) {

        initMocks( this );
    }

    @Test
    public void canCreateInstance( ) {
        RandomTrialGenerator generator = new RandomTrialGenerator( mockLocationCollection, mockSoundCollection );
    }

    @Test
    public void generateNextTrial( ) {
        when(mockLocationCollection.getRandomLocation()).thenReturn( new Location( 0,0 ) );
        when(mockSoundCollection.getRandomSoundPlayer()).thenReturn( new SoundPlayer( mockMediaPlayer ) );

        RandomTrialGenerator generator = new RandomTrialGenerator( mockLocationCollection, mockSoundCollection );

        Trial trial = generator.nextTrial();

        verify( mockLocationCollection ).getRandomLocation();
        verify( mockSoundCollection ).getRandomSoundPlayer();

        assertNotNull( trial );

        assertEquals( 0, trial.getLocation().getCol() );
        assertEquals( 0, trial.getLocation().getRow() );
    }
}
