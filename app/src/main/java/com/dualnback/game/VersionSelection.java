package com.dualnback.game;


import com.dualnback.dao.DataDto;
import com.dualnback.dao.DataPoint;

import java.util.List;

import static com.dualnback.game.NBackVersion.TwoBack;

class VersionSelection {

    protected static final NBackVersion DEFAULT_VERSION_WHEN_MISSING = TwoBack;
    protected static final int MIN_REQUIRED_SC0RE_TO_GO_TO_NEXT_LVL = 80;

    private final DataDto dataDto;

    VersionSelection( DataDto dataDto ) {
        this.dataDto = dataDto;
    }

    public NBackVersion currentLevel( ) {
        DataDto sortedDataByDate = dataDto.sortedDataPoints();

        List<DataPoint> scoreData = sortedDataByDate.userDataPoints();

        if ( scoreData.size() > 0 ) {
            DataPoint lastDataPoint = scoreData.get( scoreData.size() - 1 );
            if ( lastDataPoint.score() >= MIN_REQUIRED_SC0RE_TO_GO_TO_NEXT_LVL ) {
                return lastDataPoint.version().nextVersionUp().orElse( DEFAULT_VERSION_WHEN_MISSING );
            } else {
                return lastDataPoint.version();
            }
        }

        return DEFAULT_VERSION_WHEN_MISSING;
    }


}
