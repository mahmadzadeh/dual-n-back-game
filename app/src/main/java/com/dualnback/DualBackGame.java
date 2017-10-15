package com.dualnback;


import com.dualnback.location.Location;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;
import com.dualnback.sound.SoundPlayer;

import org.apache.commons.collections4.queue.CircularFifoQueue;

public class DualBackGame {

    private final DualBackGrid dualBackGrid;
    private final NBackVersion version;
    private final CircularFifoQueue<SoundLocation> history;

    public DualBackGame( DualBackGrid dualBackGrid, NBackVersion version ) {
        this.dualBackGrid = dualBackGrid;
        this.version = version;
        this.history = new CircularFifoQueue<>( version.howFarBack );
    }


    public SoundLocation getRandomSoundAndLocation( SoundCollection soundCollection, LocationCollection locationCollection ) {

        SoundPlayer randomSoundPlayer = soundCollection.getRandomSoundPlayer();
        Location randomLocation = locationCollection.getRandomLocation();

        return new SoundLocation( randomSoundPlayer, randomLocation );
    }

    public void storeInHistory( SoundLocation newSoundAndLoc ) {
        history.add( newSoundAndLoc );
    }

    public CircularFifoQueue<SoundLocation> getCopyOfHistory( ) {
        return new CircularFifoQueue<>( this.history );
    }

    public UserInputEvaluation evaluateSound( SoundLocation userInput ) {
        return UserInputEvaluation.IncorrectSound;
    }

    public DualBackGame playOneRound( ) {

        return null;
    }
}
