package com.dualnback.game;

import com.dualnback.location.Location;

import java.util.Optional;

import static com.dualnback.game.UserInput.LocationMatch;
import static com.dualnback.game.UserInput.NoInput;
import static com.dualnback.game.UserInput.SoundMatch;
import static com.dualnback.game.UserInputEvaluation.CorrectLocation;
import static com.dualnback.game.UserInputEvaluation.CorrectSound;
import static com.dualnback.game.UserInputEvaluation.IncorrectLocation;
import static com.dualnback.game.UserInputEvaluation.IncorrectSound;

public class DualBackGame {

    private final DualBackGrid dualBackGrid;

    private final GameTrialCollection gameTrialCollection;
    private UserInput soundMatch;
    private UserInput locationMatch;
    private Score score;
    private Optional<Trial> currentTrial;

    public DualBackGame( DualBackGrid dualBackGrid, GameTrialCollection gameTrialCollection ) {

        this.dualBackGrid = dualBackGrid;
        this.score = new Score( gameTrialCollection.totalSoundMatches(), gameTrialCollection.totalLocationMatches() );
        this.gameTrialCollection = gameTrialCollection;
        this.currentTrial = null;
    }

    public Optional<Trial> getNextTrial( ) {
        return gameTrialCollection.hasNext()
                ? Optional.of( gameTrialCollection.next() )
                : Optional.empty();
    }

    public boolean recordSoundMatch( ) {
        this.soundMatch = SoundMatch;
        return currentTrial
                .map( t -> t.getUserInput().isSoundMatch( this.soundMatch ) )
                .orElse( false );
    }

    public boolean recordLocationMatch( ) {
        this.locationMatch = LocationMatch;
        return currentTrial.
                map( t -> t.getUserInput().isLocationMatch( this.locationMatch ) )
                .orElse( false );
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

    public Optional<Trial> nextTrial( ) {
        return getNextTrial();
    }

    public Optional<Cell> markStartOfTrial( ) {
        currentTrial = getNextTrial();
        return currentTrial.map( ct -> turnOnCellAtLocation( ct.getLocation() ) );
    }

    public Optional<Cell> markEndOfTrial( ) {
        currentTrial.ifPresent( trial -> calculateScore( trial ) );
        // clear current sound/location  match
        this.soundMatch = null;
        this.locationMatch = null;

        return turnOffCurrentOnCell();
    }

    private void calculateScore( Trial trial ) {
        if ( trial.getUserInput().getLocationMatch() == NoInput && this.locationMatch != null ) {
            score.update( IncorrectLocation );
        } else if ( trial.getUserInput().getLocationMatch() == NoInput && this.locationMatch == null ) {
            // do nothing
        } else if ( trial.getUserInput().getLocationMatch() == this.locationMatch ) {
            score.update( CorrectLocation );
        } else if ( trial.getUserInput().getLocationMatch() != this.locationMatch ) {
            score.update( IncorrectLocation );
        }
        if ( trial.getUserInput().getSoundMatch() == NoInput && this.soundMatch != null ) {
            score.update( IncorrectSound );
        } else if ( trial.getUserInput().getSoundMatch() == NoInput && this.soundMatch == null ) {
            // do nothing
        } else if ( trial.getUserInput().getSoundMatch() == this.soundMatch ) {
            score.update( CorrectSound );
        } else if ( trial.getUserInput().getSoundMatch() != this.soundMatch ) {
            score.update( IncorrectSound );
        }
    }

    public Optional<Location> findCellLocation( Cell cell ) {
        return dualBackGrid.locationOfCell( cell );
    }

    public Optional<Trial> getCurrentTrial( ) {
        return currentTrial;
    }
}