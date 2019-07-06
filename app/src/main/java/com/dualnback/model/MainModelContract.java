package com.dualnback.model;

import com.dualnback.game.Cell;
import com.dualnback.location.Location;

import java.util.Optional;

interface MainModelContract {

    interface Model {

        boolean recordLocationMatch( );

        boolean recordSoundMatch( );

        Optional<Cell> markEndOfTrial( );

        Cell markStartOfTrial( );

        double getCurrentScore( );

        double currentPoints( );

        Optional<Location> findCellLocation( Cell cell );
    }
}
