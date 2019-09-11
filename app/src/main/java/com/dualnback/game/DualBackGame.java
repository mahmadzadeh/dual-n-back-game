package com.dualnback.game;

import com.dualnback.data.location.Location;

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
    private Optional<UserInput> soundMatch;
    private Optional<UserInput> locationMatch;
    private Optional<Trial> currentTrial;
    private Score score;

    public DualBackGame( DualBackGrid dualBackGrid, GameTrialCollection gameTrialCollection ) {

        this.dualBackGrid = dualBackGrid;
        this.score = new Score( gameTrialCollection.totalSoundMatches(), gameTrialCollection.totalLocationMatches() );
        this.gameTrialCollection = gameTrialCollection;
        this.currentTrial = Optional.empty();
        this.locationMatch = Optional.empty();
        this.soundMatch = Optional.empty();
    }

    public Optional<Trial> getNextTrial( ) {
        return gameTrialCollection.hasNext()
                ? Optional.of( gameTrialCollection.next() )
                : Optional.empty();
    }

    public boolean recordSoundMatch( ) {
        this.soundMatch = Optional.of( SoundMatch );
        return currentTrial
                .map( t -> t.getUserInput().isSoundMatch( this.soundMatch.get() ) )
                .orElse( false );
    }

    public boolean recordLocationMatch( ) {
        this.locationMatch = Optional.of( LocationMatch );
        return currentTrial.
                map( t -> t.getUserInput().isLocationMatch( this.locationMatch.get() ) )
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

    public Optional<Cell> markStartOfTrial( ) {
        currentTrial = getNextTrial();
        return currentTrial.map( ct -> turnOnCellAtLocation( ct.getLocation() ) );
    }

    public Optional<Cell> markEndOfTrial( ) {
        currentTrial.ifPresent( trial -> calculateScore( trial ) );
        // clear current sound/location  match
        this.soundMatch = Optional.empty();
        this.locationMatch = Optional.empty();

        return turnOffCurrentOnCell();
    }

    private void calculateScore( Trial trial ) {

        if ( trial.getUserInput().getLocationMatch() == NoInput && this.locationMatch.isPresent() ) {
            score.update( IncorrectLocation );
        } else if ( trial.getUserInput().getLocationMatch() == NoInput && !this.locationMatch.isPresent() ) {
            // do nothing
        } else if ( trial.getUserInput().getLocationMatch() == LocationMatch && !this.locationMatch.isPresent() ) {
            score.update( IncorrectLocation );
        } else if ( trial.getUserInput().getLocationMatch() == LocationMatch && this.locationMatch.isPresent() &&
                this.locationMatch.get() == LocationMatch ) {
            score.update( CorrectLocation );
        } else {
            score.update( IncorrectLocation );
        }

        if ( trial.getUserInput().getSoundMatch() == NoInput && this.soundMatch.isPresent() ) {
            score.update( IncorrectSound );
        } else if ( trial.getUserInput().getSoundMatch() == NoInput && !this.soundMatch.isPresent() ) {
            // do nothing
        } else if ( trial.getUserInput().getSoundMatch() == SoundMatch && !this.soundMatch.isPresent() ) {
            score.update( IncorrectSound );
        } else if ( trial.getUserInput().getSoundMatch() == SoundMatch && this.soundMatch.isPresent() ) {
            score.update( CorrectSound );
        } else {
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