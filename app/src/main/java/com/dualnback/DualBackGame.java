package com.dualnback;

import com.dualnback.location.Location;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.Sound;
import com.dualnback.sound.SoundCollection;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import static com.dualnback.UserInputEvaluation.CorrectSound;
import static com.dualnback.UserInputEvaluation.IncorrectSound;

public class DualBackGame {

    private final DualBackGrid dualBackGrid;
    private final NBackVersion version;
    private final CircularFifoQueue<SoundLocation> history;
    private Score score;

    public DualBackGame( DualBackGrid dualBackGrid, NBackVersion version ) {
        this.dualBackGrid = dualBackGrid;
        this.version = version;
        this.history = new CircularFifoQueue<>( version.howFarBack );
        this.score = new Score();
    }


    public SoundLocation getRandomSoundAndLocation( SoundCollection soundCollection, LocationCollection locationCollection ) {

        Sound randomSound = soundCollection.getRandomSoundPlayer();
        Location randomLocation = locationCollection.getRandomLocation();

        return new SoundLocation( randomSound, randomLocation );
    }

    public void storeInHistory( SoundLocation newSoundAndLoc ) {
        history.add( newSoundAndLoc );
    }

    public CircularFifoQueue<SoundLocation> getCopyOfHistory( ) {
        return new CircularFifoQueue<>( this.history );
    }

    public UserInputEvaluation evaluateSound( Sound soundInput ) {
        if ( history.isEmpty() ) {
            return IncorrectSound;
        }

        if ( soundInput.matches( history.get( version.howFarBack - 1 ).getSound() ) ) {
            this.score = this.score.update( CorrectSound );

            return CorrectSound;

        } else {

            return UserInputEvaluation.IncorrectSound;

        }

    }

    public double getScore( ) {
        return this.score.calculateScorePercentage();
    }

    public DualBackGame playOneRound( ) {

        return null;
    }
}
