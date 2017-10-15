package com.dualnback;

import org.junit.Test;

import static com.dualnback.UserInputEvaluation.IncorrectLocation;
import static com.dualnback.UserInputEvaluation.CorrectLocation;
import static com.dualnback.UserInputEvaluation.CorrectSound;
import static com.dualnback.UserInputEvaluation.IncorrectSound;
import static org.junit.Assert.assertEquals;


public class ScoreTest {

    public static final double DELTA = 0.01;

    @Test
    public void givenTotalNumberOfTrialsAndCorrectGuessForLocationAndSoundThenCanCalculateFinalScore( ) {

        int totalTrials = 15;
        int numCorrectLocationGuess = 10;
        int numCorrectSoundGuess = 10;
        double expected = 66.67;

        assertEquals( expected,
                new Score( totalTrials, numCorrectLocationGuess, numCorrectSoundGuess ).calculateScorePercentage(),
                DELTA );
    }

    @Test
    public void testScoreCalculationWhenAllCorrectAnswersAreGiven( ) {

        int totalTrials = 3;
        int numCorrectLocationGuess = 3;
        int numCorrectSoundGuess = 3;
        double expected = 100.00;

        assertEquals( expected,
                new Score( totalTrials, numCorrectLocationGuess, numCorrectSoundGuess ).calculateScorePercentage(),
                DELTA );
    }

    @Test
    public void testScoreCalculationWhenNoCorrectAnswersAreGiven( ) {

        int totalTrials = 3;
        int numCorrectLocationGuess = 0;
        int numCorrectSoundGuess = 0;
        double expected = 0;

        assertEquals( expected,
                new Score( totalTrials, numCorrectLocationGuess, numCorrectSoundGuess ).calculateScorePercentage(),
                DELTA );
    }

    @Test
    public void canCalculateScoreForNewlyCreatedObject( ) {

        assertEquals( 0, new Score().calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreBasedOnUserInput( ) {

        Score score = new Score()
                .update( CorrectSound )
                .updateTrialsByeOne();

        assertEquals( 50.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreBasedOnIncorrectUserInput( ) {

        Score score = new Score().update( IncorrectSound );

        assertEquals( 0.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreBasedOnCorrectSoundUserInputButIncorrectLocation( ) {

        Score score = new Score()
                .update( CorrectSound )
                .updateTrialsByeOne();

        assertEquals( 50.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreBasedOnCorrectLocationUserInputButIncorrectSound( ) {

        Score score = new Score()
                .update( IncorrectLocation )
                .updateTrialsByeOne();

        assertEquals( 0.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreBasedOnMultipleInputs( ) {

        Score score = new Score()
                .update( CorrectSound )
                .update( IncorrectLocation )
                .updateTrialsByeOne()
                .update( IncorrectSound )
                .update( IncorrectLocation )
                .updateTrialsByeOne();

        assertEquals( 25.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreWhenUserAnswersAllCorrect( ) {

        Score score = new Score()
                .update( CorrectSound )
                .update( CorrectLocation )
                .updateTrialsByeOne()
                .update( CorrectSound )
                .update( CorrectLocation )
                .updateTrialsByeOne() ;

        assertEquals( 100.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreWhenUserAnswersAllincorrectly( ) {

        Score score = new Score()
                .update( IncorrectSound )
                .update( IncorrectLocation )
                .updateTrialsByeOne()
                .update( IncorrectSound )
                .update( IncorrectLocation )
                .updateTrialsByeOne() ;

        assertEquals( 0.00, score.calculateScorePercentage(), DELTA );
    }
}