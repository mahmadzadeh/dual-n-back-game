package com.dualnback;

import com.dualnback.location.Location;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.Sound;
import com.dualnback.sound.SoundCollection;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Optional;

import static com.dualnback.UserInputEvaluation.CorrectLocation;
import static com.dualnback.UserInputEvaluation.CorrectSound;
import static com.dualnback.UserInputEvaluation.IncorrectLocation;
import static com.dualnback.UserInputEvaluation.IncorrectSound;

public class DualBackGame {

    private final DualBackGrid dualBackGrid;
    private final NBackVersion version;
    private final CircularFifoQueue<SoundLocation> history;
    private Score score;

    private Optional<SoundLocation> currentSoundLocation;

    public DualBackGame( DualBackGrid dualBackGrid, NBackVersion version ) {
        this.dualBackGrid = dualBackGrid;
        this.version = version;
        this.history = new CircularFifoQueue<>( version.howFarBack );
        this.score = new Score();
        this.currentSoundLocation = Optional.empty();
    }

    public SoundLocation getRandomSoundAndLocation( SoundCollection soundCollection, LocationCollection locationCollection ) {

        Sound randomSound = soundCollection.getRandomSound();
        Location randomLocation = locationCollection.getRandomLocation();

        return new SoundLocation( randomSound, randomLocation );
    }

    public void updateGame( SoundLocation soundLocation ) {

        currentSoundLocation = Optional.of( soundLocation );

        storeInHistory( soundLocation );

        dualBackGrid.updateGridAtLocation( soundLocation.getLocation() );
    }


    public UserInputEvaluation evaluateSound( ) {
        if ( history.isEmpty() ) {
            return IncorrectSound;
        }

        return currentSoundLocation
                .filter( snl -> snl.getSound().matches( getItemFromHistory().getSound() ) )
                .map( matchingSnl -> {
                            score = score.update( CorrectSound );
                            return CorrectSound;
                        }
                ).orElse( IncorrectSound );
    }

    public UserInputEvaluation evaluateLocation( ) {
        if ( history.isEmpty() ) {
            return IncorrectLocation;
        }

        return currentSoundLocation
                .filter( snl -> snl.getLocation().matches( getItemFromHistory().getLocation() ) )
                .map( matchingSnl -> {
                            score = score.update( CorrectLocation );
                            return CorrectLocation;
                        }
                ).orElse( IncorrectLocation );
    }

    public void storeInHistory( SoundLocation newSoundAndLoc ) {
        history.add( newSoundAndLoc );
    }

    public CircularFifoQueue<SoundLocation> getCopyOfHistory( ) {
        return new CircularFifoQueue<>( this.history );
    }


    public double getScorePercentage( ) {
        return this.score.calculateScorePercentage();
    }


    public void markEndOfOneRound( ) {
        score = score.updateTrialsByeOne();
    }

    public Optional<SoundLocation> getCurrentSoundLocation( ) {
        return currentSoundLocation;
    }

    private SoundLocation getItemFromHistory( ) {
        return history.get( version.howFarBack - 1 );
    }
}
