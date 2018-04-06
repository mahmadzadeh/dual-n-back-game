package com.dualnback.game;

import java.util.Iterator;

class AlternativeDualBackGame {

    private final DualBackGrid dualBackGrid;
    private final GameTrialCollection gameTrialCollection;
    private final Iterator<Trial> trialIterator;

    private UserInput soundMatch;
    private UserInput locationMatch;
    private ScoreAlt score;

    public AlternativeDualBackGame( DualBackGrid dualBackGrid, GameTrialCollection gameTrialCollection ) {

        this.dualBackGrid = dualBackGrid;
        this.gameTrialCollection = gameTrialCollection;
        this.trialIterator = gameTrialCollection.iterator();
        this.score = new ScoreAlt( gameTrialCollection.totalSoundMatches(), gameTrialCollection.totalLocationMatches() );
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
