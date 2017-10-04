package com.dualnback;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ScoreTest {

    private Score subjectUnderTest = new Score();


    @Test
    public void givenTotalNumberOfTrialsAndCorrectGuessForLocationAndSoundThenCanCalculateFinalScore( ) {

        int totalTrials = 15;
        int numCorrectLocationGuess = 10;
        int numCorrectSoundGuess = 10;
        double expected = 66.67;

        assertEquals( expected,
                subjectUnderTest.calculateScorePercentage( totalTrials, numCorrectLocationGuess, numCorrectSoundGuess ),
                0.01);
    }
}