package com.dualnback.game;


public class Score {

    private final int numCorrectLocationGuess;
    private final int numCorrectSoundGuess;
    private final int totalTrials;

    public Score( ) {
        this.numCorrectLocationGuess = 0;
        this.numCorrectSoundGuess = 0;
        this.totalTrials = 0;
    }

    public Score( int totalTrials, int numCorrectLocationGuess, int numCorrectSoundGuess ) {
        this.numCorrectLocationGuess = numCorrectLocationGuess;
        this.numCorrectSoundGuess = numCorrectSoundGuess;
        this.totalTrials = totalTrials;
    }


    public double calculateScorePercentage( ) {
        if ( totalTrials == 0 ) {
            return 0.00;
        }

        double correctLocPercentage = ( 100 * numCorrectLocationGuess ) / ( double ) totalTrials;
        double correctSoundPercentage = ( 100 * numCorrectSoundGuess ) / ( double ) totalTrials;

        return ( correctLocPercentage + correctSoundPercentage ) / 2;
    }

    public Score update( UserInputEvaluation userInputEvaluation ) {
        switch ( userInputEvaluation ) {
            case CorrectSound:
                return new Score( this.totalTrials, this.numCorrectLocationGuess, this.numCorrectSoundGuess + 1 );
            case IncorrectSound:
                return new Score( this.totalTrials, numCorrectLocationGuess, numCorrectSoundGuess );
            case CorrectLocation:
                return new Score( this.totalTrials, numCorrectLocationGuess + 1, numCorrectSoundGuess );
            case IncorrectLocation:
                return new Score( this.totalTrials, numCorrectLocationGuess, numCorrectSoundGuess );
            default:
                throw new IllegalArgumentException( "Invalid user input given " + userInputEvaluation );
        }
    }

    public Score updateTrialsByeOne( ) {
        return new Score( this.totalTrials + 1, this.numCorrectLocationGuess, this.numCorrectSoundGuess );
    }

    public Score clone( ) {
        return new Score( totalTrials, numCorrectLocationGuess, numCorrectSoundGuess );
    }
}
