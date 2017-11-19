package com.dualnback.random;

import com.dualnback.game.Trial;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;

public class RandomTrialGenerator {

    private final LocationCollection locationCollection;
    private final SoundCollection soundCollection;

    public RandomTrialGenerator( LocationCollection locationCollection, SoundCollection soundCollection ) {

        this.locationCollection = locationCollection;
        this.soundCollection = soundCollection;
    }

    public Trial nextTrial( ) {
        return new Trial( locationCollection.getRandomLocation(), soundCollection.getRandomSound() );
    }
}
