package com.dualnback.game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.dualnback.game.UserInput.LocationMatch;
import static com.dualnback.game.UserInput.NoInput;
import static com.dualnback.game.UserInput.SoundMatch;
import static java.util.stream.IntStream.range;

public class GameTrialCollection {

    private final List<Trial> trials;

    private final NBackVersion version;

    public GameTrialCollection( NBackVersion version, List<Trial> trials ) {
        this.version = version;
        this.trials = new ArrayList<>( trials );

        initTrials();
    }

    public int totalSoundMatches( ) {
        Predicate<Trial> trialPredicate = trial -> trial.getUserInput().getSoundMatch() == SoundMatch;
        return filterByPredicate( trialPredicate );
    }

    public int totalLocationMatches( ) {
        Predicate<Trial> trialPredicate = trial -> trial.getUserInput().getLocationMatch() == LocationMatch;
        return filterByPredicate( trialPredicate );
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
                soundMatch = SoundMatch;
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

    private int filterByPredicate( Predicate<Trial> trialPredicate ) {
        return trials
                .stream()
                .filter( trialPredicate )
                .collect( Collectors.toList() )
                .size();
    }

    @Override
    public String toString( ) {
        return "GameTrialCollection{" +
                "version=" + version +
                "trials=" + this.trials.stream().map( t -> t.toString() ).collect( Collectors.joining( ", \n" ) ) +
                '}';
    }
}
