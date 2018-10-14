package com.dualnback.game;

import org.junit.Test;

import static com.dualnback.game.UserInputEvaluation.CorrectLocation;
import static com.dualnback.game.UserInputEvaluation.CorrectSound;
import static com.dualnback.game.UserInputEvaluation.IncorrectLocation;
import static com.dualnback.game.UserInputEvaluation.IncorrectSound;
import static org.junit.Assert.assertEquals;


public class ScoreTest {

    private static final double DELTA = 0.1;

    @Test
    public void givenZeroTotalSoundAndLocationMatchAndNoUserInputThenCorrectScoreCalculated( ) {
        double score = new Score( 0, 0 )
                .calculateScorePercentage();

        assertEquals( 0.0, score, DELTA );
    }

    @Test
    public void givenZeroTotalSoundAndLocationMatchAndWhenOneWrongAnswerGivenThenCorrectScoreCalculated( ) {
        double score = new Score( 0, 0 )
                .update( IncorrectSound )
                .calculateScorePercentage();

        assertEquals( 0.0, score, 0.00001 );
    }

    @Test
    public void givenZeroTotalSoundAndLocationMatchAndWhenTwoWrongAnswersGivenThenCorrectScoreCalculated( ) {
        double score = new Score( 0, 0 )
                .update( IncorrectSound )
                .update( IncorrectLocation )
                .calculateScorePercentage();

        assertEquals( 0.0, score, 0.00001 );
    }

    @Test
    public void givenOneSoundAndOneLocationMatchAndWhenOneWrongAnswersAndOneCorrectGivenThenCorrectScoreCalculated( ) {
        double score = new Score( 1, 1 )
                .update( CorrectSound )
                .update( IncorrectLocation )
                .calculateScorePercentage();

        assertEquals( 0.0, score, 0.00001 );
    }

    @Test
    public void givenOneSoundAndOneLocationMatchAndWhenTwoRightAnswersThenCorrectScoreCalculated( ) {
        double score = new Score( 1, 1 )
                .update( CorrectSound )
                .update( CorrectLocation )
                .calculateScorePercentage();

        assertEquals( 100.0, score, 0.00001 );
    }

    @Test
    public void givenSixSoundAndSixLocationMatchAndWhenFourCorrectLocationAndThreeCorrectSoundAnswersGivenThenCorrectScoreCalculated( ) {
        double score = new Score( 6, 6 )
                .update( CorrectSound )
                .update( CorrectSound )
                .update( CorrectSound )
                .update( CorrectLocation )
                .update( CorrectLocation )
                .update( CorrectLocation )
                .update( CorrectLocation )
                .calculateScorePercentage();

        assertEquals( 58.3, score, DELTA );
    }

    @Test
    public void givenSevenSoundAndFourLocationMatchAndWhenAllMatchedCorrectlyThen_100ScoreGiven( ) {
        double score = new Score( 7, 4 )
                .update( CorrectSound )
                .update( CorrectSound )
                .update( CorrectSound )
                .update( CorrectSound )
                .update( CorrectSound )
                .update( CorrectSound )
                .update( CorrectSound )
                .update( CorrectLocation )
                .update( CorrectLocation )
                .update( CorrectLocation )
                .update( CorrectLocation )
                .calculateScorePercentage();

        assertEquals( 100, score, DELTA );
    }

}