package com.dualnback.game;

class Score {

    private int countOfRightSoundGuesses;
    private int countOfWrongSoundGuesses;

    private int countOfRightLocationGuesses;
    private int countOfWrongLocationGuesses;

    private int expectedTotalSoundMatch;
    private int expectedTotalLocationMatch;

    private double singlePoint;

    public Score( int expectedTotalSoundMatch, int expectedTotalLocationMatch ) {
        this.expectedTotalSoundMatch = expectedTotalSoundMatch;
        this.expectedTotalLocationMatch = expectedTotalLocationMatch;

        this.countOfRightSoundGuesses = 0;
        this.countOfWrongSoundGuesses = 0;

        this.countOfRightLocationGuesses = 0;
        this.countOfWrongLocationGuesses = 0;

        singlePoint = divideIfDivisorOrDefault(
                100d,
                expectedTotalLocationMatch + expectedTotalSoundMatch,
                0.0 );
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
                this.countOfWrongSoundGuesses++;
                return this;
            case IncorrectLocation:
                this.countOfWrongLocationGuesses++;
                return this;
            default:
                throw new IllegalArgumentException( "Invalid user input given " + userInputEvaluation );
        }
    }

    public double calculateScorePercentage( ) {
        double correctSoundScore = singlePoint * countOfRightSoundGuesses;
        double correctLocationScore = singlePoint * countOfRightLocationGuesses;

        double incorrectSoundScore = singlePoint * countOfWrongSoundGuesses;
        double incorrectLocationScore = singlePoint * countOfWrongLocationGuesses;

        double correct = correctSoundScore + correctLocationScore;
        double incorrect = incorrectLocationScore + incorrectSoundScore;

        return correct - incorrect;
    }

    private double divideIfDivisorOrDefault( double numerator, int denom, double defaultNum ) {
        return denom == 0 || numerator == 0 ? defaultNum : ( numerator / denom );
    }

    @Override
    public String toString( ) {
        return "Score{" +
                "expectedTotalSoundMatch=" + expectedTotalSoundMatch +
                ", expectedTotalLocationMatch=" + expectedTotalLocationMatch +
                ", countOfRightLocationGuesses=" + countOfRightLocationGuesses +
                ", countOfRightSoundGuesses=" + countOfRightSoundGuesses +
                '}';
    }
}
