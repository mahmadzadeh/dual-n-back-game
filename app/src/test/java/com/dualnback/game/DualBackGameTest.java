package com.dualnback.game;

import com.dualnback.location.Location;
import com.dualnback.sound.SSound;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class DualBackGameTest {

    @Mock
    DualBackGrid dualBackGrid;

    GameTrialCollection gameTrialCollection;

    Alternative alternative;

    List<Trial> trials = Arrays.asList(
            new Trial( new Location( 0, 0 ), new SSound( 1 ) ) );

    @Before
    public void setUp( ) {
        gameTrialCollection = new GameTrialCollection( NBackVersion.TwoBack, trials );
        alternative = new Alternative( dualBackGrid, gameTrialCollection );
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


    private class Alternative {

        private final DualBackGrid dualBackGrid;
        private final GameTrialCollection gameTrialCollection;
        private final Iterator<Trial> trialIterator;

        private UserInput soundMatch;
        private UserInput locationMatch;
        private Score score;

        public Alternative( DualBackGrid dualBackGrid, GameTrialCollection gameTrialCollection ) {

            this.dualBackGrid = dualBackGrid;
            this.gameTrialCollection = gameTrialCollection;
            this.trialIterator = gameTrialCollection.iterator();
            this.score = new Score();
        }

        public Score getScore( ) {
            return score.clone();
        }

        public Trial getNextTrial( ) {
            return trialIterator.hasNext() ? trialIterator.next() : null;
        }


        public void recordSoundMatch( ) {
            this.soundMatch = UserInput.SoundMatch;
        }

        public void recordLocationMatch( ) {
            this.locationMatch = UserInput.LocationMatch;
        }


        public void markEndOfTrial( Trial currentTrial ) {
            UserInput soundMatch = this.soundMatch;
            UserInput locMatch = this.locationMatch;

            this.score = score.updateTrialsByeOne();

            if ( currentTrial.getUserInput().isLocationMatch( locMatch ) ) {
                score = score.update( UserInputEvaluation.CorrectLocation );
            } else {
                score = score.update( UserInputEvaluation.IncorrectLocation );
            }

            if ( currentTrial.getUserInput().isSoundMatch( soundMatch ) ) {
                score = score.update( UserInputEvaluation.CorrectSound );
            } else {
                score = score.update( UserInputEvaluation.IncorrectSound );
            }

            // clear current sound/location  match

            this.soundMatch = null;
            this.locationMatch = null;
        }

        public double getCurrentScore( ) {
            return score.calculateScorePercentage();
        }
    }
}