package com.dualnback.game.factory;

import com.dualnback.data.util.random.RandomTrialGenerator;
import com.dualnback.game.NBackVersion;
import com.dualnback.game.Trial;
import com.dualnback.util.IntegerRange;

import java.util.List;
import java.util.stream.Collectors;

import static com.dualnback.data.util.random.RandomNumberGenerator.next_N_DistinctRandomIntsWithinRange;
import static com.dualnback.ui.mainscreen.MainActivity.TOTAL_TRIAL_COUNT;
import static java.util.stream.IntStream.range;

public class TrialListFactory {

    public static List<Trial> create( int count, RandomTrialGenerator randomTrialGenerator ) {
        return range( 0, count )
                .mapToObj( i -> randomTrialGenerator.nextTrial() )
                .collect( Collectors.toList() );
    }

    public static List<Trial> create( RandomTrialGenerator randomTrialGenerator ) {
        return create( TOTAL_TRIAL_COUNT, randomTrialGenerator );
    }


    public static List<Trial> updateListWithExpectedSoundAndLocationMatch( List<Trial> trials,
                                                                           int expectedSoundMatches,
                                                                           int expectedLocMatches,
                                                                           NBackVersion version ) {
        int lowerBound = 0 + version.getHowFarBack();
        int upperBound = trials.size() - 1;

        IntegerRange range = new IntegerRange( lowerBound, upperBound );
        next_N_DistinctRandomIntsWithinRange( expectedSoundMatches, range )
                .stream()
                .forEach( integer -> {
                            Trial trial = trials.get( integer );
                            int indexOfNTrialsAgo = getIndexOfNTrialsAgo( integer, version );
                            Trial toBeChanged = trials.get( indexOfNTrialsAgo );
                            trials.set( indexOfNTrialsAgo, new Trial( toBeChanged.getLocation(), trial.getSound() ) );
                        }
                );

        next_N_DistinctRandomIntsWithinRange( expectedLocMatches, range )
                .stream()
                .forEach( integer -> {
                            Trial trial = trials.get( integer );
                            int indexOfNTrialsAgo = getIndexOfNTrialsAgo( integer, version );
                            Trial toBeChanged = trials.get( indexOfNTrialsAgo );
                            trials.set( indexOfNTrialsAgo, new Trial( trial.getLocation(), toBeChanged.getSound() ) );
                        }
                );

        return trials;
    }

    public static int getIndexOfNTrialsAgo( int currIndex, NBackVersion version ) {
        return currIndex - version.getHowFarBack();
    }


}
