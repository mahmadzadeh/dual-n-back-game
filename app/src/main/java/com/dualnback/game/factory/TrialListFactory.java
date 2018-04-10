package com.dualnback.game.factory;

import com.dualnback.game.Trial;
import com.dualnback.random.RandomTrialGenerator;

import java.util.List;
import java.util.stream.Collectors;

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
}
