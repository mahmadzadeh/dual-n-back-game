package com.dualnback.game;

import com.dualnback.location.Location;
import com.dualnback.sound.BSound;
import com.dualnback.sound.SSound;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static com.dualnback.game.NBackVersion.TwoBack;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class DualBackGameTest {

    private final SSound sSound = new SSound( 1 );
    private final BSound bSound = new BSound( 2 );

    @Mock
    DualBackGrid dualBackGrid;

    GameTrialCollection gameTrialCollection;

    AlternativeDualBackGame alternative;

    List<Trial> trials = Arrays.asList(
            new Trial( new Location( 0, 0 ), new SSound( 1 ) ) );

    @Before
    public void setUp( ) {
        gameTrialCollection = new GameTrialCollection( TwoBack, trials );
        alternative = new AlternativeDualBackGame( dualBackGrid, gameTrialCollection );
    }

    @Test
    public void givenInstanceThenGetNextSoundLocationReturns( ) {
        Trial trial = alternative.getNextTrial();

        assertNotNull( trial );
    }

    @Test
    public void givenStartOfATrialThenRecordSoundMatchRecordsASingleSoundMatch( ) {

        alternative.recordSoundMatch();
    }

    @Test
    public void givenStartOfTrialThenRecordLocationMatchRecordsSingleLocationMatch( ) {
        alternative.recordLocationMatch();
    }

    @Test
    public void givenSingleTrialAndTwoNBackThenMarkEndOfTrialUpdatesScoreAccordingly( ) {
        Trial currTrial = alternative.getNextTrial();

        alternative.markEndOfTrial( currTrial );

        double score = alternative.getCurrentScore();

        assertEquals( 0.0, score, 0.0001 );
    }

    @Test
    public void givenThreeTrialsAndTwoNBackWhenNoInputFromUserThenMarkEndOfTrialUpdatesScoreAccordingly( ) {

        trials = Arrays.asList(
                new Trial( new Location( 0, 0 ), new SSound( 1 ) ),
                new Trial( new Location( 1, 1 ), bSound ),
                new Trial( new Location( 0, 0 ), new SSound( 1 ) ) );

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        alternative = new AlternativeDualBackGame( dualBackGrid, gameTrialCollection );

        alternative.markEndOfTrial( alternative.getNextTrial() );
        alternative.markEndOfTrial( alternative.getNextTrial() );
        alternative.markEndOfTrial( alternative.getNextTrial() );

        double score = alternative.getCurrentScore();

        assertEquals( 0.0, score, 0.0001 );
    }

    @Test
    public void givenThreeTrialsAndTwoNBackWhenOneWrongInputFromUserThenMarkEndOfTrialUpdatesScoreAccordingly( ) {

        trials = Arrays.asList(
                new Trial( new Location( 0, 0 ), new SSound( 1 ) ),
                new Trial( new Location( 1, 1 ), new SSound( 2 ) ),
                new Trial( new Location( 0, 0 ), new SSound( 1 ) ) );

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        alternative = new AlternativeDualBackGame( dualBackGrid, gameTrialCollection );

        Trial nextTrial = alternative.getNextTrial();

        alternative.recordLocationMatch();
        alternative.recordSoundMatch();

        alternative.markEndOfTrial( nextTrial );

        nextTrial = alternative.getNextTrial();
        alternative.markEndOfTrial( nextTrial );


        nextTrial = alternative.getNextTrial();
        alternative.markEndOfTrial( nextTrial );

        double score = alternative.getCurrentScore();

        assertEquals( 0.0, score, 0.0001 );
    }

    @Test
    public void givenFourTrialsAndTwoNBackWhenUserGuessesFiftyPercentCorrectThenMarkEndOfTrialUpdatesScoreAccordingly( ) {


        trials = Arrays.asList(
                new Trial( new Location( 0, 0 ), sSound ),
                new Trial( new Location( 1, 1 ), bSound ),
                new Trial( new Location( 0, 0 ), sSound ),
                new Trial( new Location( 1, 1 ), bSound ) );

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        alternative = new AlternativeDualBackGame( dualBackGrid, gameTrialCollection );

        Trial nextTrial = alternative.getNextTrial();
        alternative.markEndOfTrial( nextTrial );

        nextTrial = alternative.getNextTrial();
        alternative.markEndOfTrial( nextTrial );

        nextTrial = alternative.getNextTrial();
        alternative.recordLocationMatch();
        alternative.recordSoundMatch();
        alternative.markEndOfTrial( nextTrial );

        nextTrial = alternative.getNextTrial();
        alternative.markEndOfTrial( nextTrial );

        double score = alternative.getCurrentScore();

        assertEquals( 50.0, score, 0.0001 );
    }

}