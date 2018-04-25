package com.dualnback.game;

import com.dualnback.location.Location;

import java.util.Iterator;
import java.util.Optional;

import static com.dualnback.game.UserInput.LocationMatch;
import static com.dualnback.game.UserInput.SoundMatch;
import static com.dualnback.game.UserInputEvaluation.CorrectLocation;
import static com.dualnback.game.UserInputEvaluation.CorrectSound;
import static com.dualnback.game.UserInputEvaluation.IncorrectLocation;
import static com.dualnback.game.UserInputEvaluation.IncorrectSound;

public class DualBackGame {

    private final DualBackGrid dualBackGrid;
    private final GameTrialCollection gameTrialCollection;
    private final Iterator<Trial> trialIterator;

    private UserInput soundMatch;
    private UserInput locationMatch;
    private Score score;

    public DualBackGame( DualBackGrid dualBackGrid, GameTrialCollection gameTrialCollection ) {

        this.dualBackGrid = dualBackGrid;
        this.gameTrialCollection = gameTrialCollection;
        this.trialIterator = gameTrialCollection.getTrials().iterator();
        this.score = new Score( gameTrialCollection.totalSoundMatches(), gameTrialCollection.totalLocationMatches() );
    }

    public Trial getNextTrial( ) {
        return trialIterator.hasNext() ? trialIterator.next() : null;
    }

    public void recordSoundMatch( ) {
        this.soundMatch = SoundMatch;
    }

    public void recordLocationMatch( ) {
        this.locationMatch = LocationMatch;
    }

    public void markEndOfTrial( Trial currentTrial ) {

        if ( currentTrial == null ) {
            this.soundMatch = null;
            this.locationMatch = null;
            return;
        }


        if ( currentTrial.getUserInput().isLocationMatch( this.locationMatch ) ) {
            score = score.update( CorrectLocation );
        } else {
            score = score.update( IncorrectLocation );
        }

        if ( currentTrial.getUserInput().isSoundMatch( this.soundMatch ) ) {
            score = score.update( CorrectSound );
        } else {
            score = score.update( IncorrectSound );
        }

        // clear current sound/location  match

        this.soundMatch = null;
        this.locationMatch = null;
    }

    public double getCurrentScore( ) {
        return score.calculateScorePercentage();
    }

    public Optional<Cell> turnOffCurrentOnCell( ) {

        return dualBackGrid
                .getTurnedOnCell()
                .map( cell -> {
                    cell.turnOff();
                    return cell;
                } );
    }

    public Cell turnOnCellAtLocation( Location location ) {
        return dualBackGrid
                .turnOnCellAtLocation( location );
    }

    public Trial markStartOfTrial( ) {
        Trial trial = getNextTrial();

        turnOffCurrentOnCell();

        turnOnCellAtLocation( trial.getLocation() );

        return trial;
    }


    public Optional<Location> findCellLocation( Cell cell ) {
        return dualBackGrid.locationOfCell( cell );
    }
}
