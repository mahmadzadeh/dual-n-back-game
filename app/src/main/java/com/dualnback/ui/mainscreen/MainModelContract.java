package com.dualnback.ui.mainscreen;

import com.dualnback.data.location.Location;
import com.dualnback.game.Cell;
import com.dualnback.game.Trial;

import java.util.Optional;

interface MainModelContract {

    interface Model {

        boolean recordLocationMatch( );

        boolean recordSoundMatch( );

        Optional<Cell> markEndOfTrial( );

        Optional<Cell> markStartOfTrial( );

        double getCurrentScore( );

        double currentPoints( );

        Optional<Location> findCellLocation( Cell cell );

        Optional<Trial> getCurrentTrial( );
    }
}
