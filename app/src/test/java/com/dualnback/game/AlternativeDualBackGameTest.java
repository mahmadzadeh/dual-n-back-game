package com.dualnback.game;

import android.support.annotation.NonNull;

import com.dualnback.location.Location;
import com.dualnback.sound.BSound;
import com.dualnback.sound.SSound;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.dualnback.game.NBackVersion.TwoBack;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlternativeDualBackGameTest {

    private final SSound sSound = new SSound( 1 );
    private final BSound bSound = new BSound( 2 );

    @Mock
    AlternativeDualBackGrid dualBackGrid;

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


        trials = getTestTrials();

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

    @Test
    public void givenCurrentGameStateWhenNoCellInGridTurnedOnThenTurnOffOnGridCellDoesNothing( ) {

        trials = getTestTrials();

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        alternative = new AlternativeDualBackGame( dualBackGrid, gameTrialCollection );

        when( dualBackGrid.getTurnedOnCell() ).thenReturn( Optional.empty() );

        assertEquals( Optional.empty(), alternative.turnOffOnCell() );

        verify( dualBackGrid ).getTurnedOnCell();
    }

    @Test
    public void givenCurrentGameStateWhenOneCellInGridTurnedOnThenTurnOffOnGridCellTurnsOffCell( ) {
        Cell expectedOffCell = new Cell( 1, 2 );

        trials = getTestTrials();

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        alternative = new AlternativeDualBackGame( dualBackGrid, gameTrialCollection );

        when( dualBackGrid.getTurnedOnCell() ).thenReturn( Optional.of( expectedOffCell ) );

        Optional<Cell> onCell = alternative.turnOffOnCell();

        assertTrue( onCell.isPresent() );

        assertTrue( !onCell.get().isTurnedOn() );
    }

    @Test
    public void givenLocationThenCanTurnOnCellAtLocation( ) {
        Location location = new Location( 0, 0 );

        Cell expectedOnCell = new Cell( 1, 2 );
        expectedOnCell.turnOn();

        trials = getTestTrials();

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        alternative = new AlternativeDualBackGame( dualBackGrid, gameTrialCollection );

        when( dualBackGrid.turnOnCellAtLocation( location ) ).thenReturn( expectedOnCell );

        Cell turnOnCell = alternative.turnOnCellAtLocation( location );

        assertTrue( turnOnCell.isTurnedOn() );

        verify( dualBackGrid ).turnOnCellAtLocation( location );
    }

    @NonNull
    private List<Trial> getTestTrials( ) {
        return Arrays.asList(
                new Trial( new Location( 0, 0 ), sSound ),
                new Trial( new Location( 1, 1 ), bSound ),
                new Trial( new Location( 0, 0 ), sSound ),
                new Trial( new Location( 1, 1 ), bSound ) );
    }


}