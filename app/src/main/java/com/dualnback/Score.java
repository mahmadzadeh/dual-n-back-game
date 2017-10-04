package com.dualnback;


import java.text.DecimalFormat;

public class Score {

    public static final String PATTERN = "#0.00";
    DecimalFormat formatter = new DecimalFormat( "#0.00" );

    public double calculateScorePercentage( int totalTrials, int numCorrectLocationGuess, int numCorrectSoundGuess ) {

        double correctLocPercentage = ( 100 * numCorrectLocationGuess ) / (double)totalTrials;
        double correctSoundPercentage = ( 100 * numCorrectSoundGuess ) / (double)totalTrials;

        return ( correctLocPercentage + correctSoundPercentage ) / 2;
    }
}
