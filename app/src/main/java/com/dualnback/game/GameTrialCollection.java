package com.dualnback.game;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dualnback.game.UserInput.NoInput;
import static java.util.stream.IntStream.range;

class GameTrialCollection implements Iterable<Trial> {

    private final List<Trial> trials;

    private final NBackVersion version;

    public GameTrialCollection( NBackVersion version, List<Trial> trials ) {
        this.version = version;
        this.trials = new ArrayList<>( trials );

        initTrials();
    }

    public List<Trial> getTrials( ) {
        return new ArrayList<>( trials );
    }

    private void initTrials( ) {
        range( 0, trials.size() )
                .forEach( i -> {

                    Trial nTrialsBack = getNTrialsBack( i );
                    updateCurrentTrialsUserInput( trials.get( i ), nTrialsBack );

                } );
    }

    private void updateCurrentTrialsUserInput( Trial currentTrial, Trial nTrialsBack ) {
        setUserInput( currentTrial, nTrialsBack );
    }

    private void setUserInput( Trial trial, Trial nTrialsBack ) {
        if ( nTrialsBack == null ) {
            trial.setUserInput( new ExpectedUserInput( NoInput, NoInput ) );
        } else {
            UserInput localMatch = UserInput.NoInput;
            UserInput soundMatch = UserInput.NoInput;
            if ( nTrialsBack.getLocation().equals( trial.getLocation() ) ) {
                localMatch = UserInput.LocationMatch;
            }
            if ( nTrialsBack.getSound().equals( trial.getSound() ) ) {
                soundMatch = UserInput.SoundMatch;
            }
            trial.setUserInput( new ExpectedUserInput( soundMatch, localMatch ) );
        }
    }

    private Trial getNTrialsBack( int currIndex ) {
        if ( currIndex - version.howFarBack < 0 ) {
            return null;
        } else {
            return trials.get( currIndex - version.howFarBack );
        }
    }

    @NonNull
    @Override
    public Iterator<Trial> iterator( ) {
        return this.trials.iterator();
    }
}
