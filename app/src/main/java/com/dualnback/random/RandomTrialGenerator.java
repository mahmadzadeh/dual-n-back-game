package com.dualnback.random;

import com.dualnback.game.ExpectedUserInput;
import com.dualnback.game.Trial;
import com.dualnback.game.UserInput;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomTrialGenerator {

    private final LocationCollection locationCollection;
    private final SoundCollection soundCollection;

    public RandomTrialGenerator( LocationCollection locationCollection, SoundCollection soundCollection ) {

        this.locationCollection = locationCollection;
        this.soundCollection = soundCollection;
    }

    public Trial nextTrial( ) {
        Trial trial = new Trial( locationCollection.getRandomLocation(), soundCollection.getRandomSound() );
        trial.setUserInput( new ExpectedUserInput( UserInput.NoInput, UserInput.NoInput ) );

        return trial;
    }


    public List<Trial> listOfRandomTrials( int count ) {
        return IntStream
                .range( 0, count )
                .mapToObj( i -> nextTrial() )
                .collect( Collectors.toList() );
    }
}
