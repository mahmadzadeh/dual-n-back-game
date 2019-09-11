package com.dualnback.data.util.random;

import android.media.MediaPlayer;

import com.dualnback.data.location.Location;
import com.dualnback.data.location.LocationCollection;
import com.dualnback.data.sound.KSound;
import com.dualnback.data.sound.SoundCollection;
import com.dualnback.game.Trial;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.dualnback.game.UserInput.NoInput;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RandomTrialGeneratorTest {

    @Mock
    private LocationCollection mockLocationCollection;

    @Mock
    private SoundCollection mockSoundCollection;

    @Mock
    private MediaPlayer mockMediaPlayer;


    @Test
    public void canCreateInstance( ) {
        RandomTrialGenerator generator = new RandomTrialGenerator( mockLocationCollection, mockSoundCollection );
    }

    @Test
    public void generateNextTrial( ) {
        when( mockLocationCollection.getRandomLocation() )
                .thenReturn( new Location( 0, 0 ) );
        when( mockSoundCollection.getRandomSound() )
                .thenReturn( new KSound( 1111 ) );

        RandomTrialGenerator generator = new RandomTrialGenerator( mockLocationCollection, mockSoundCollection );

        Trial trial = generator.nextTrial();

        verify( mockLocationCollection ).getRandomLocation();
        verify( mockSoundCollection ).getRandomSound();

        assertNotNull( trial );

        assertEquals( 0, trial.getLocation().getCol() );
        assertEquals( 0, trial.getLocation().getRow() );
    }


    @Test
    public void getListOfRandomTrials( ) {

        when( mockLocationCollection.getRandomLocation() )
                .thenReturn( new Location( 0, 0 ) )
                .thenReturn( new Location( 1, 1 ) )
                .thenReturn( new Location( 2, 2 ) );

        when( mockSoundCollection.getRandomSound() )
                .thenReturn( new KSound( 1111 ) )
                .thenReturn( new KSound( 1112 ) )
                .thenReturn( new KSound( 1113 ) );


        RandomTrialGenerator generator = new RandomTrialGenerator( mockLocationCollection, mockSoundCollection );

        int expectedTrialCount = 3;

        List<Trial> trials = generator.listOfRandomTrials( expectedTrialCount );

        assertEquals( expectedTrialCount, trials.size() );

        trials.stream().forEach( t -> {
            assertEquals( NoInput, t.getUserInput().getSoundMatch() );
            assertEquals( NoInput, t.getUserInput().getSoundMatch() );
        } );

        verify( mockLocationCollection, times( expectedTrialCount ) ).getRandomLocation();
        verify( mockSoundCollection, times( expectedTrialCount ) ).getRandomSound();
    }


}
