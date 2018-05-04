package com.dualnback.game.factory;

import com.dualnback.game.NBackVersion;
import com.dualnback.game.Trial;
import com.dualnback.random.RandomTrialGenerator;
import com.dualnback.util.IntegerRange;

import java.util.List;
import java.util.stream.Collectors;

import static com.dualnback.random.RandomNumberGenerator.next_N_DistinctRandomIntsWithinRange;
import static java.util.stream.IntStream.range;

public class TrialListFactory {
    private static int DEFAULT_TRIAL_COUNT_PER_GAME = 24;

    public static List<Trial> create( int count, RandomTrialGenerator randomTrialGenerator ) {
        return range( 0, count )
                .mapToObj( i -> randomTrialGenerator.nextTrial() )
                .collect( Collectors.toList() );
    }

    public static List<Trial> create( RandomTrialGenerator randomTrialGenerator ) {
        return create( DEFAULT_TRIAL_COUNT_PER_GAME, randomTrialGenerator );
    }


    public static List<Trial> updateListWithExpectedSoundAndLocationMatch( List<Trial> trials,
                                                                           int expectedSoundMatches,
                                                                           int expectedLocMacthes,
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

        next_N_DistinctRandomIntsWithinRange( expectedLocMacthes, range )
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
