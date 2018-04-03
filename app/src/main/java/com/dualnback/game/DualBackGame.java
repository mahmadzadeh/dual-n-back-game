package com.dualnback.game;

import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Optional;

import static com.dualnback.game.UserInputEvaluation.CorrectLocation;
import static com.dualnback.game.UserInputEvaluation.CorrectSound;
import static com.dualnback.game.UserInputEvaluation.IncorrectLocation;
import static com.dualnback.game.UserInputEvaluation.IncorrectSound;
import static com.dualnback.random.RandomBoolean.trueWithFiftyPercentChance;

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

        if ( trueWithFiftyPercentChance() ) {

            return history.peek();

        } else {

            return new SoundLocation( soundCollection.getRandomSound(), locationCollection.getRandomLocation() );
        }
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
        return;
    }

    public Optional<SoundLocation> getCurrentSoundLocation( ) {
        return currentSoundLocation;
    }

    private SoundLocation getItemFromHistory( ) {
        return history.get( version.howFarBack - 1 );
    }
}
