package com.dualnback;

import org.junit.Test;

import static com.dualnback.UserInputEvaluation.IncorrectSoundCorrectLocation;
import static com.dualnback.UserInputEvaluation.CorrectSoundAndLocation;
import static com.dualnback.UserInputEvaluation.CorrectSoundIncorrectLocation;
import static com.dualnback.UserInputEvaluation.IncorrectSoundAndLocation;
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

        Score score = new Score().update( CorrectSoundAndLocation );

        assertEquals( 100.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreBasedOnIncorrectUserInput( ) {

        Score score = new Score().update( IncorrectSoundAndLocation );

        assertEquals( 0.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreBasedOnCorrectSoundUserInputButIncorrectLocation( ) {

        Score score = new Score().update( CorrectSoundIncorrectLocation );

        assertEquals( 50.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreBasedOnCorrectLocationUserInputButIncorrectSound( ) {

        Score score = new Score().update( IncorrectSoundCorrectLocation );

        assertEquals( 50.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreBasedOnMultipleInputs( ) {

        Score score = new Score()
                .update( CorrectSoundAndLocation )
                .update( IncorrectSoundCorrectLocation )
                .update( IncorrectSoundAndLocation )
                .update( IncorrectSoundCorrectLocation );

        assertEquals( 50.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreWhenUserAnswersAllCorrect( ) {

        Score score = new Score()
                .update( CorrectSoundAndLocation )
                .update( CorrectSoundAndLocation )
                .update( CorrectSoundAndLocation )
                .update( CorrectSoundAndLocation ) ;

        assertEquals( 100.00, score.calculateScorePercentage(), DELTA );
    }

    @Test
    public void updatingScoreWhenUserAnswersAllincorrectly( ) {

        Score score = new Score()
                .update( IncorrectSoundAndLocation )
                .update( IncorrectSoundAndLocation )
                .update( IncorrectSoundAndLocation )
                .update( IncorrectSoundAndLocation ) ;

        assertEquals( 0.00, score.calculateScorePercentage(), DELTA );
    }
}