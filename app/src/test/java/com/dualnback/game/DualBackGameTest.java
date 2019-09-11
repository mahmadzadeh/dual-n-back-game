package com.dualnback.game;

import android.support.annotation.NonNull;

import com.dualnback.data.location.Location;
import com.dualnback.data.sound.BSound;
import com.dualnback.data.sound.SSound;
import com.dualnback.game.factory.GridFactory;

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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DualBackGameTest {

    private final SSound sSound = new SSound( 1 );
    private final BSound bSound = new BSound( 2 );

    @Mock
    private DualBackGrid dualBackGrid;

    private GameTrialCollection gameTrialCollection;

    private DualBackGame sut;

    private List<Trial> trials = Arrays.asList(
            new Trial( new Location( 0, 0 ), new SSound( 1 ) ) );

    private Trial currentTrial = trials.get( 0 );

    @Before
    public void setUp( ) {
        gameTrialCollection = new GameTrialCollection( TwoBack, trials );
        sut = new DualBackGame( dualBackGrid, gameTrialCollection );
    }

    @Test
    public void givenInstanceThenGetNextSoundLocationReturns( ) {
        Optional<Trial> trial = sut.getNextTrial();

        assertNotNull( trial );
    }

    @Test
    public void givenStartOfATrialThenRecordSoundMatchRecordsASingleSoundMatch( ) {
        sut.markStartOfTrial();

        sut.recordSoundMatch();
    }

    @Test
    public void givenStartOfTrialThenRecordLocationMatchRecordsSingleLocationMatch( ) {
        sut.markStartOfTrial();

        sut.recordLocationMatch();
    }

    @Test
    public void givenSingleTrialAndTwoNBackThenMarkEndOfTrialUpdatesScoreAccordingly( ) {
        when( dualBackGrid.getTurnedOnCell() )
                .thenReturn( Optional.of( new Cell( 2, 2 ) ) );

        sut.markStartOfTrial();

        sut.markEndOfTrial();

        double score = sut.getCurrentScore();

        assertEquals( 0.0, score, 0.0001 );
    }

    @Test
    public void givenThreeTrialsAndTwoNBackWhenNoInputFromUserThenMarkEndOfTrialUpdatesScoreAccordingly( ) {
        expectTrunedOnCellCall();

        trials = Arrays.asList(
                new Trial( new Location( 0, 0 ), new SSound( 1 ) ),
                new Trial( new Location( 1, 1 ), bSound ),
                new Trial( new Location( 0, 0 ), new SSound( 1 ) ) );

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        sut = new DualBackGame( dualBackGrid, gameTrialCollection );

        sut.markStartOfTrial();
        sut.markEndOfTrial();

        sut.markStartOfTrial();
        sut.markEndOfTrial();

        sut.markStartOfTrial();
        sut.markEndOfTrial();

        double score = sut.getCurrentScore();

        assertEquals( -100, score, 0.0001 );
    }

    @Test
    public void givenThreeTrialsAndTwoNBackWhenOneWrongInputFromUserThenMarkEndOfTrialUpdatesScoreAccordingly( ) {
        expectTrunedOnCellCall();

        trials = Arrays.asList(
                new Trial( new Location( 0, 0 ), new SSound( 1 ) ),
                new Trial( new Location( 1, 1 ), new SSound( 2 ) ),
                new Trial( new Location( 0, 0 ), new SSound( 1 ) ) );

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        sut = new DualBackGame( dualBackGrid, gameTrialCollection );

        sut.markStartOfTrial();
        sut.recordLocationMatch();
        sut.recordSoundMatch();
        sut.markEndOfTrial();


        sut.markStartOfTrial();
        sut.markEndOfTrial();

        sut.markStartOfTrial();
        sut.markEndOfTrial();

        double score = sut.getCurrentScore();

        assertEquals( -200, score, 0.0001 );
    }

    @Test
    public void givenFourTrialsAndTwoNBackWhenUserGuessesFiftyPercentCorrectThenMarkEndOfTrialUpdatesScoreAccordingly( ) {

        expectTrunedOnCellCall();

        trials = getTestTrials();

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        sut = new DualBackGame( dualBackGrid, gameTrialCollection );

        sut.markStartOfTrial();
        sut.markEndOfTrial();

        sut.markStartOfTrial();
        sut.markEndOfTrial();

        sut.markStartOfTrial();
        sut.recordLocationMatch();
        sut.recordSoundMatch();
        sut.markEndOfTrial();

        sut.markStartOfTrial();
        sut.markEndOfTrial();

        double score = sut.getCurrentScore();

        assertEquals( 0, score, 0.0001 );
    }

    @Test
    public void givenCurrentGameStateWhenNoCellInGridTurnedOnThenTurnOffOnGridCellDoesNothing( ) {

        trials = getTestTrials();

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        sut = new DualBackGame( dualBackGrid, gameTrialCollection );

        when( dualBackGrid.getTurnedOnCell() ).thenReturn( Optional.empty() );

        assertEquals( Optional.empty(), sut.turnOffCurrentOnCell() );

        verify( dualBackGrid ).getTurnedOnCell();
    }

    @Test
    public void givenCurrentGameStateWhenOneCellInGridTurnedOnThenTurnOffOnGridCellTurnsOffCell( ) {
        Cell expectedOffCell = new Cell( 1, 2 );

        trials = getTestTrials();

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        sut = new DualBackGame( dualBackGrid, gameTrialCollection );

        when( dualBackGrid.getTurnedOnCell() ).thenReturn( Optional.of( expectedOffCell ) );

        Optional<Cell> onCell = sut.turnOffCurrentOnCell();

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

        sut = new DualBackGame( dualBackGrid, gameTrialCollection );

        when( dualBackGrid.turnOnCellAtLocation( location ) ).thenReturn( expectedOnCell );

        Cell turnOnCell = sut.turnOnCellAtLocation( location );

        assertTrue( turnOnCell.isTurnedOn() );

        verify( dualBackGrid ).turnOnCellAtLocation( location );
    }

    @Test
    public void giveInvalidCellThenFindCellLocationReturnOptionalEmpty( ) {

        Cell expectedOnCell = new Cell( 1, 2 );
        expectedOnCell.turnOn();

        trials = getTestTrials();

        gameTrialCollection = new GameTrialCollection( TwoBack, trials );

        sut = new DualBackGame( dualBackGrid, gameTrialCollection );

        Cell nonExistentCell = new Cell( 22, 222 );

        Optional<Location> loc = sut.findCellLocation( nonExistentCell );
    }

    /**
     * Some kind of integration test of all collaborators
     */
    @Test
    public void playTwoBackWithThreeTrials( ) {
        expectTrunedOnCellCall();

        sut = new DualBackGame( GridFactory.instance(),
                new GameTrialCollection( TwoBack, getTestTrials() ) );

        Optional<Cell> offCell = sut.turnOffCurrentOnCell();
        assertFalse( offCell.isPresent() ); // initially nothing is turned on

        sut.markStartOfTrial();
        sut.markEndOfTrial();

        sut.markStartOfTrial();
        sut.markEndOfTrial();

        sut.markStartOfTrial();
        sut.recordLocationMatch();
        sut.recordSoundMatch();
        sut.markEndOfTrial();

        sut.markStartOfTrial();
        sut.recordLocationMatch();
        sut.recordSoundMatch();
        sut.markEndOfTrial();

        double currentScore = sut.getCurrentScore();

        assertEquals( 100.00, currentScore, 0.0001 );
    }


    private void expectTrunedOnCellCall( ) {
        when( dualBackGrid.getTurnedOnCell() )
                .thenReturn( Optional.of( new Cell( 2, 2 ) ) )
                .thenReturn( Optional.of( new Cell( 2, 2 ) ) )
                .thenReturn( Optional.of( new Cell( 2, 2 ) ) )
                .thenReturn( Optional.of( new Cell( 2, 2 ) ) );
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