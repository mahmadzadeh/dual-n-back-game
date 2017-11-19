package com.dualnback.game;


import com.dualnback.location.Location;
import com.dualnback.location.NullLocation;

import java.util.ArrayList;
import java.util.List;

class DualBackGrid {

    List<List<Cell>> grid = new ArrayList<>();
    private Location currentTurnedOnCell;

    public DualBackGrid( List<List<Cell>> grid ) {
        this.grid = grid;
        this.currentTurnedOnCell = NullLocation.INSTANCE();
    }

    public void updateGridAtLocation( Location location ) {
        currentTurnedOnCell = location;

        turnOffTheOnCell();

        turnOnCellAtCurrentLocation();
    }

    private void turnOnCellAtCurrentLocation( ) {
        grid.get( currentTurnedOnCell.getRow() )
                .get( currentTurnedOnCell.getCol() )
                .turnOn();
    }

    private void turnOffTheOnCell( ) {

        if ( thereIsCellToTurnOff() ) {
            grid.get( this.currentTurnedOnCell.getRow() )
                    .get( this.currentTurnedOnCell.getCol() )
                    .turnOff();
        }

    }

    private boolean thereIsCellToTurnOff( ) {
        return !( currentTurnedOnCell instanceof NullLocation );
    }

    public Location getTurnedOnCell( ) {
        return currentTurnedOnCell;
    }
}
