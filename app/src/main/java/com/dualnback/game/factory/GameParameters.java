package com.dualnback.game.factory;

import com.dualnback.game.NBackVersion;
import com.dualnback.location.LocationCollection;
import com.dualnback.settings.ApplicationConfig;
import com.dualnback.sound.SoundCollection;
import com.dualnback.view.MainScreenView;

public class GameParameters {

    private NBackVersion version;
    private MainScreenView context;
    private int expectedSoundMatches;
    private int expectedLocMatches;
    private LocationCollection locationCollection;
    private SoundCollection soundCollection;

    private ApplicationConfig config;

    public NBackVersion version( ) {
        return version;
    }

    public MainScreenView getContext( ) {
        return context;
    }

    public int getExpectedSoundMatches( ) {
        return expectedSoundMatches;
    }

    public int getExpectedLocMatches( ) {
        return expectedLocMatches;
    }

    public LocationCollection locCollection( ) {
        return locationCollection;
    }

    public SoundCollection soundCollection( ) {
        return soundCollection;
    }

    public ApplicationConfig getConfig( ) {
        return config;
    }

    public GameParameters withVersion( NBackVersion version ) {
        this.version = version;
        return this;
    }

    public GameParameters withContext( MainScreenView context ) {
        this.context = context;
        return this;
    }

    public GameParameters withExpectedSoundMatches( int expectedSoundMatches ) {
        this.expectedSoundMatches = expectedSoundMatches;
        return this;
    }

    public GameParameters withExpectedLocationMatches( int expectedLocMatches ) {
        this.expectedLocMatches = expectedLocMatches;
        return this;
    }

    public GameParameters withLocationCollection( LocationCollection locationCollection ) {
        this.locationCollection = locationCollection;
        return this;
    }

    public GameParameters withSoundCollection( SoundCollection soundCollection ) {
        this.soundCollection = soundCollection;
        return this;
    }

    public GameParameters withConfig( ApplicationConfig config ) {
        this.config = config;
        return this;
    }
}
