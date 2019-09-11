package com.dualnback.game;


import com.dualnback.data.location.Location;
import com.dualnback.data.sound.BSound;
import com.dualnback.data.sound.JSound;
import com.dualnback.data.sound.KSound;
import com.dualnback.data.sound.SSound;
import com.dualnback.data.util.random.RandomTrialGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.dualnback.game.UserInput.LocationMatch;
import static com.dualnback.game.UserInput.NoInput;
import static com.dualnback.game.UserInput.SoundMatch;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTrialCollectionTest {

    private final SSound sSound = new SSound( 11 );
    private final BSound bSound = new BSound( 12 );
    private final KSound cSound = new KSound( 13 );
    private final JSound jSound = new JSound( 14 );
    private final KSound kSound = new KSound( 15 );

    @Mock
    RandomTrialGenerator randomTrialGenerator;


    @Before
    public void setUp( ) {
        when( randomTrialGenerator.nextTrial() )
                .thenReturn( new Trial( new Location( 0, 0 ), sSound ) )
                .thenReturn( new Trial( new Location( 1, 1 ), sSound ) )
                .thenReturn( new Trial( new Location( 2, 2 ), sSound ) );
    }

    @Test
    public void canCreateTrialCollection( ) {
        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.TwoBack, new ArrayList<>() );
    }

    @Test
    public void givenConstructedGameTrialCollectionThenGetTrialsReturnsCollectionOfTrials( ) {
        List<Trial> trials = new GameTrialCollection( NBackVersion.TwoBack, new ArrayList<>() ).getTrials();

        assertEquals( 0, trials.size() );
    }

    @Test
    public void givenListOfTrialsOfOneThenExpectedResultsForFirstTrialIsNoSoundAndNoLocationMatch( ) {

        List<Trial> trials = new ArrayList<Trial>() {{
            add( new Trial( new Location( 0, 0 ), sSound ) );
        }};

        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.TwoBack, trials );

        List<Trial> actual = gameTrialCollection.getTrials();

        assertEquals( trials.size(), actual.size() );

        Trial trial = actual.get( 0 );

        assertExpectedUserInput( trial, NoInput, NoInput );
    }

    @Test
    public void givenOneBackGameThenTwoAdjacentTrialsWithMatchingSoundLocationHaveCorrectUserInputs( ) {

        List<Trial> trials = new ArrayList<Trial>() {{
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 0, 0 ), sSound ) );
        }};

        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.OneBack, trials );

        List<Trial> actual = gameTrialCollection.getTrials();

        assertEquals( trials.size(), actual.size() );
        assertExpectedUserInput( actual.get( 0 ), NoInput, NoInput );
        assertExpectedUserInput( actual.get( 1 ), LocationMatch, SoundMatch );
    }

    @Test
    public void givenOneBackGameThenTwoAdjacentTrialsWithSoundOnlyHaveCorrectUserInputs( ) {

        List<Trial> trials = new ArrayList<Trial>() {{
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 0, 1 ), sSound ) );
        }};

        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.OneBack, trials );

        List<Trial> actual = gameTrialCollection.getTrials();

        assertEquals( trials.size(), actual.size() );
        assertExpectedUserInput( actual.get( 0 ), NoInput, NoInput );
        assertExpectedUserInput( actual.get( 1 ), NoInput, SoundMatch );
    }

    @Test
    public void givenTwoBackGameThenTwoNonAdjacentTrialsWithMatchingSoundLocationHaveCorrectUserInputs( ) {

        List<Trial> trials = new ArrayList<Trial>() {{
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 1, 2 ), bSound ) );
            add( new Trial( new Location( 0, 0 ), sSound ) );
        }};

        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.TwoBack, trials );

        List<Trial> actual = gameTrialCollection.getTrials();

        assertExpectedUserInput( actual.get( 0 ), NoInput, NoInput );
        assertExpectedUserInput( actual.get( 1 ), NoInput, NoInput );
        assertExpectedUserInput( actual.get( 2 ), LocationMatch, SoundMatch );
    }

    @Test
    public void givenThreeBackGameThenTwoNonAdjacentTrialsWithMatchingSoundLocationHaveCorrectUserInputs( ) {

        List<Trial> trials = new ArrayList<Trial>() {{
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 1, 2 ), bSound ) );
            add( new Trial( new Location( 0, 0 ), cSound ) );
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 1, 2 ), jSound ) );
            add( new Trial( new Location( 1, 2 ), kSound ) );
            add( new Trial( new Location( 2, 2 ), sSound ) );
        }};

        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.ThreeBack, trials );

        List<Trial> actual = gameTrialCollection.getTrials();

        assertExpectedUserInput( actual.get( 0 ), NoInput, NoInput );
        assertExpectedUserInput( actual.get( 1 ), NoInput, NoInput );
        assertExpectedUserInput( actual.get( 2 ), NoInput, NoInput );
        assertExpectedUserInput( actual.get( 3 ), LocationMatch, SoundMatch );
        assertExpectedUserInput( actual.get( 4 ), LocationMatch, NoInput );
        assertExpectedUserInput( actual.get( 5 ), NoInput, NoInput );
        assertExpectedUserInput( actual.get( 6 ), NoInput, SoundMatch );
    }

    @Test
    public void givenGameTrialCollectionThenTotalSoundAndLocationMatchesRetunsTotalExpectedMatchCount( ) {
        List<Trial> trials = new ArrayList<Trial>() {{
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 1, 2 ), bSound ) );
            add( new Trial( new Location( 0, 0 ), kSound ) );
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 1, 2 ), jSound ) );
            add( new Trial( new Location( 1, 2 ), kSound ) );
            add( new Trial( new Location( 2, 2 ), sSound ) );
        }};

        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.ThreeBack, trials );

        assertEquals( 3, gameTrialCollection.totalSoundMatches() );

        assertEquals( 2, gameTrialCollection.totalLocationMatches() );
    }

    @Test
    public void iterableInerfaceIsImplementedCorrectlyWithASingleTrial( ) {
        List<Trial> trials = new ArrayList<Trial>() {{
            add( new Trial( new Location( 0, 0 ), sSound ) );
        }};

        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.ThreeBack, trials );

        assertThat( gameTrialCollection.hasNext() ).isTrue();

        assertThat( gameTrialCollection.next() ).isEqualTo( trials.get( 0 ) );
        assertThat( gameTrialCollection.hasNext() ).isFalse();
    }

    @Test
    public void iterableInterfaceIsImplementedCorrectlyWithTwoTrial( ) {
        List<Trial> trials = new ArrayList<Trial>() {{
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 1, 2 ), bSound ) );
        }};

        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.ThreeBack, trials );

        assertThat( gameTrialCollection.hasNext() ).isTrue();
        assertThat( gameTrialCollection.next() ).isEqualTo( trials.get( 0 ) );

        assertThat( gameTrialCollection.hasNext() ).isTrue();
        assertThat( gameTrialCollection.next() ).isEqualTo( trials.get( 1 ) );

        assertThat( gameTrialCollection.hasNext() ).isFalse();

    }

    @Test
    public void iterableInterfaceIsImplementedCorrectlyWithMultipleTrial( ) {
        List<Trial> trials = new ArrayList<Trial>() {{
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 1, 2 ), bSound ) );
            add( new Trial( new Location( 0, 0 ), kSound ) );
            add( new Trial( new Location( 0, 0 ), sSound ) );
            add( new Trial( new Location( 1, 2 ), jSound ) );
            add( new Trial( new Location( 1, 2 ), kSound ) );
            add( new Trial( new Location( 2, 2 ), sSound ) );
        }};

        GameTrialCollection gameTrialCollection = new GameTrialCollection( NBackVersion.ThreeBack, trials );

        for ( Trial trial : trials ) {

            assertThat( gameTrialCollection.hasNext() ).isTrue();
            assertThat( gameTrialCollection.next() ).isEqualTo( trial );
        }

        assertThat( gameTrialCollection.hasNext() ).isFalse();

    }

    private void assertExpectedUserInput( Trial trial, UserInput locationInput, UserInput soundInput ) {
        assertEquals( locationInput, trial.getUserInput().getLocationMatch() );
        assertEquals( soundInput, trial.getUserInput().getSoundMatch() );
    }

}
