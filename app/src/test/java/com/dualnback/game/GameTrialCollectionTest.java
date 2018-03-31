package com.dualnback.game;


import com.dualnback.location.Location;
import com.dualnback.random.RandomTrialGenerator;
import com.dualnback.sound.SSound;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTrialCollectionTest {

    @Mock
    RandomTrialGenerator randomTrialGenerator;


    @Before
    public void setUp( ) {
        when( randomTrialGenerator.nextTrial() )
                .thenReturn( new Trial( new Location( 0, 0 ), new SSound( 11 ) ) )
                .thenReturn( new Trial( new Location( 1, 1 ), new SSound( 11 ) ) )
                .thenReturn( new Trial( new Location( 2, 2 ), new SSound( 11 ) ) );
    }

    @Test
    public void canCreateTrialCollection( ) {
        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.TwoBack, randomTrialGenerator );
    }

    @Test
    public void givenConstructedGameTrialCollectionThenGetTrialsReturnsCollectionOfTrials( ) {
        int numberOfTrials = 3;
        List<Trial> trials = new GameTrialCollection( NBackVersion.TwoBack, numberOfTrials, randomTrialGenerator ).getTrials();

        assertEquals( numberOfTrials, trials.size() );
    }


    @Test
    public void test( ) {
        int numberOfTrials = 3;
        List<Trial> trials = new GameTrialCollection( NBackVersion.TwoBack, numberOfTrials, randomTrialGenerator ).getTrials();

        assertEquals( numberOfTrials, trials.size() );
    }


}
