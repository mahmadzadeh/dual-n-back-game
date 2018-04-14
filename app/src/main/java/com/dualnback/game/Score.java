package com.dualnback.game;

class Score {
    private final int expectedTotalSoundMatch;
    private final int expectedTotalLocationMatch;
    private int countOfRightLocationGuesses;
    private int countOfRightSoundGuesses;


    public Score( int expectedTotalSoundMatch, int expectedTotalLocationMatch ) {
        this.expectedTotalSoundMatch = expectedTotalSoundMatch;
        this.expectedTotalLocationMatch = expectedTotalLocationMatch;
        this.countOfRightSoundGuesses = 0;
        this.countOfRightLocationGuesses = 0;
    }


    public Score update( UserInputEvaluation userInputEvaluation ) {
        switch ( userInputEvaluation ) {
            case CorrectSound:
                this.countOfRightSoundGuesses++;
                return this;
            case CorrectLocation:
                this.countOfRightLocationGuesses++;
                return this;
            case IncorrectSound:
                return this;
            case IncorrectLocation:
                return this;
            default:
                throw new IllegalArgumentException( "Invalid user input given " + userInputEvaluation );
        }
    }

    public double calculateScorePercentage( ) {

        int rightGuessPercent = 100 * countOfRightSoundGuesses;
        int righLocGuessPercent = 100 * countOfRightLocationGuesses;

        double correctSoundPercentage =
                divideIfDivisorOrDefault( rightGuessPercent, expectedTotalSoundMatch, 0 );
        double correctLocPercentage = divideIfDivisorOrDefault( righLocGuessPercent, expectedTotalLocationMatch, 0 );

        double totalPercentage = correctLocPercentage + correctSoundPercentage;


        return totalPercentage != 0
                ? totalPercentage / 2
                : 0;
    }

    private double divideIfDivisorOrDefault( int numerator, double denom, double defaultNum ) {

        return numerator != 0 ? ( numerator / denom ) : defaultNum;
    }
}
