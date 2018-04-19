package com.dualnback.game;

import com.dualnback.game.factory.TrialListFactory;
import com.dualnback.location.Location;
import com.dualnback.random.RandomTrialGenerator;
import com.dualnback.sound.GSound;
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

    @Test
    public void givenArrayOfTrialsThereIsAlwaysSixSoundMatchAndSixLocationMatch( ) {

        NBackVersion version = NBackVersion.TwoBack;

        expectTwentyFourInvocations();

        List<Trial> trials = TrialListFactory.create( mockRandomTrialGenerator );

        GameTrialCollection trialCollection = new GameTrialCollection( version, trials );

        assertEquals( 6, trialCollection.totalLocationMatches() );

        trials = TrialListFactory.updateListWithExpectedSoundAndLocationMatch( trials,
                6,
                6,
                NBackVersion.TwoBack );

    }


    /**
     * return 24 trials with only 2 sound matches and 2 location matches
     * to be used in 2N back
     */
    private void expectTwentyFourInvocations( ) {
        when( mockRandomTrialGenerator.nextTrial() )
                .thenReturn( new Trial( getLocation( 0, 0 ), SSound ) )
                .thenReturn( new Trial( getLocation( 1, 0 ), newSound( 2 ) ) )
                .thenReturn( new Trial( getLocation( 2, 0 ), SSound ) )    // sound match
                .thenReturn( new Trial( getLocation( 0, 0 ), newSound( 3 ) ) )
                .thenReturn( new Trial( getLocation( 2, 0 ), SSound ) ) // loc match and sound match
                .thenReturn( new Trial( getLocation( 1, 0 ), newSound( 5 ) ) )
                .thenReturn( new Trial( getLocation( 2, 1 ), newSound( 6 ) ) )
                .thenReturn( new Trial( getLocation( 0, 0 ), newSound( 7 ) ) )
                .thenReturn( new Trial( getLocation( 1, 1 ), newSound( 8 ) ) )
                .thenReturn( new Trial( getLocation( 1, 2 ), newSound( 9 ) ) )
                .thenReturn( new Trial( getLocation( 0, 0 ), newSound( 10 ) ) )
                .thenReturn( new Trial( getLocation( 2, 1 ), newSound( 11 ) ) )
                .thenReturn( new Trial( getLocation( 2, 2 ), newSound( 12 ) ) )
                .thenReturn( new Trial( getLocation( 1, 1 ), newSound( 2 ) ) )
                .thenReturn( new Trial( getLocation( 1, 0 ), newSound( 3 ) ) )
                .thenReturn( new Trial( getLocation( 0, 0 ), newSound( 42 ) ) )
                .thenReturn( new Trial( getLocation( 0, 0 ), newSound( 5 ) ) )
                .thenReturn( new Trial( getLocation( 1, 1 ), newSound( 6 ) ) )
                .thenReturn( new Trial( getLocation( 0, 0 ), newSound( 2 ) ) ) // loc match
                .thenReturn( new Trial( getLocation( 0, 0 ), newSound( 4 ) ) )
                .thenReturn( new Trial( getLocation( 1, 1 ), newSound( 5 ) ) )
                .thenReturn( new Trial( getLocation( 2, 0 ), newSound( 6 ) ) )
                .thenReturn( new Trial( getLocation( 1, 2 ), newSound( 7 ) ) )
                .thenReturn( new Trial( getLocation( 2, 2 ), newSound( 2 ) ) );
    }

    private Sound newSound( int i ) {
        return new GSound( i );
    }

    private Location getLocation( int row, int col ) {
        return new Location( row, col );
    }


}