package com.dualnback.game;

import java.util.ArrayList;
import java.util.List;

import static com.dualnback.game.UserInput.NoInput;

class GameTrialCollection {

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

        for ( int i = 0; i < trials.size(); i++ ) {
            Trial trial = trials.get( i );
            Trial nTrialsBack = getNTrialsBack( i );
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
    }

    private Trial getNTrialsBack( int currIndex ) {
        if ( currIndex - version.howFarBack < 0 ) {
            return null;
        } else {
            return trials.get( currIndex - version.howFarBack );
        }
    }
}
