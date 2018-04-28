package com.dualnback.game.factory;

import com.dualnback.SwappableImage;
import com.dualnback.game.NBackVersion;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;

public class GameParameters {

    private final NBackVersion version;
    private final SwappableImage context;
    private final int expectedSoundMatches;
    private final int expectedLocMacthes;
    private final LocationCollection locationCollection;
    private final SoundCollection soundCollection;

    public GameParameters( NBackVersion version,
                           SwappableImage context,
                           int expectedSoundMatches,
                           int expectedLocMatches,
                           LocationCollection locationCollection,
                           SoundCollection soundCollection ) {

        this.version = version;
        this.context = context;
        this.expectedSoundMatches = expectedSoundMatches;
        this.expectedLocMacthes = expectedLocMatches;
        this.locationCollection = locationCollection;
        this.soundCollection = soundCollection;
    }

    public NBackVersion version( ) {
        return version;
    }

    public SwappableImage getContext( ) {
        return context;
    }

    public int getExpectedSoundMatches( ) {
        return expectedSoundMatches;
    }

    public int getExpectedLocMacthes( ) {
        return expectedLocMacthes;
    }

    public LocationCollection locCollection( ) {
        return locationCollection;
    }

    public SoundCollection soundCollection( ) {
        return soundCollection;
    }
}
