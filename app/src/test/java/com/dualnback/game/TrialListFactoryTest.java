package com.dualnback.game;

import com.dualnback.game.factory.TrialListFactory;
import com.dualnback.location.Location;
import com.dualnback.random.RandomTrialGenerator;
import com.dualnback.sound.SSound;
import com.dualnback.sound.Sound;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrialListFactoryTest {

    private Location testLocation = new Location( 0, 0 );
    private Sound SSound = new SSound( 1 );

    @Mock
    private RandomTrialGenerator mockRandomTrialGenerator;

    @Test
    public void canCreateListOfZeroTrials( ) {
        List<Trial> trials = TrialListFactory.create( 0, mockRandomTrialGenerator );

        assertEquals( 0, trials.size() );

        verify( mockRandomTrialGenerator, times( 0 ) ).nextTrial();
    }

    @Test
    public void givenNumberOfTrialsThenListOfTrialsIsGenerated( ) {

        when( mockRandomTrialGenerator.nextTrial() )
                .thenReturn( new Trial( testLocation, SSound ) )
                .thenReturn( new Trial( testLocation, SSound ) )
                .thenReturn( new Trial( testLocation, SSound ) );

        List<Trial> trials = TrialListFactory.create( 3, mockRandomTrialGenerator );

        assertEquals( 3, trials.size() );

        verify( mockRandomTrialGenerator, times( 3 ) ).nextTrial();
    }

}