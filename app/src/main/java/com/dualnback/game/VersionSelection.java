package com.dualnback.game;


import com.dualnback.dao.DataPoint;

import java.util.Optional;

import static com.dualnback.game.NBackVersion.TwoBack;

public class VersionSelection {

    protected static final NBackVersion DEFAULT_VERSION_WHEN_MISSING = TwoBack;

    public static NBackVersion currentLevel( Optional<DataPoint> lastDataPoint,
                                             int minRequiredScoreToNextLvl,
                                             int minScoreToMaintainCurrLvl ) {

        return lastDataPoint
                .map( dataPoint -> getVersion( minRequiredScoreToNextLvl, minScoreToMaintainCurrLvl, dataPoint ) )
                .orElse( DEFAULT_VERSION_WHEN_MISSING );

    }

    private static NBackVersion getVersion( int minRequiredScoreToNextLvl,
                                            int minScoreToMaintainCurrLvl,
                                            DataPoint dataPoint ) {

        if ( shouldIncrementLevel( dataPoint, minRequiredScoreToNextLvl ) ) {
            return dataPoint
                    .version()
                    .nextVersionUp()
                    .orElse( dataPoint.version() );
        } else if ( shouldDecrementLevel( dataPoint, minScoreToMaintainCurrLvl ) ) {
            return dataPoint
                    .version()
                    .previousVersionDown()
                    .orElse( dataPoint.version() );
        } else {
            return dataPoint.version();
        }
    }

    private static boolean shouldIncrementLevel( DataPoint lastDataPoint, int minRequiredSc0ReToGoToNextLvl ) {
        return lastDataPoint.score() >= minRequiredSc0ReToGoToNextLvl;
    }

    private static boolean shouldDecrementLevel( DataPoint lastDataPoint, int minRequiredSc0ReToMaintainCurrentLvl ) {
        return lastDataPoint.score() < minRequiredSc0ReToMaintainCurrentLvl;
    }
}
