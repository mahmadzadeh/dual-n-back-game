package com.dualnback.game;

import com.dualnback.location.Location;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class DualBackGrid {
    private static final int ROW_CNT = 3;
    private static final int COL_CNT = 3;

    List<List<Cell>> grid;

    public DualBackGrid( List<List<Cell>> grid ) {
        if ( grid == null || grid.size() < ROW_CNT ) {
            throw new IllegalArgumentException( "Invalid Grid Given. Need a grid of 3X3" );
        }

        this.grid = copy( grid );
    }

    public Optional<Cell> getTurnedOnCell( ) {
        for ( List<Cell> row : grid ) {
            for ( Cell cell : row ) {
                if ( cell.isTurnedOn() ) {
                    return Optional.of( cell );
                }
            }
        }

        return Optional.empty();
    }

    public Cell turnOnCellAtLocation( Location location ) {

        if ( !validLocation( location ) ) {
            throw new IllegalArgumentException( "Invalid location given. " +
                    "Grid is 3X3. Given location" + location );
        }

        Cell cell = grid
                .get( location.getRow() )
                .get( location.getCol() );

        cell.turnOn();

        return cell;
    }

    public Optional<Location> getLocationOfTurnedOnCell( ) {
        for ( int row = 0; row < ROW_CNT; row++ ) {
            for ( int col = 0; col < COL_CNT; col++ ) {
                if ( this.grid.get( row ).get( col ).isTurnedOn() ) {
                    return Optional.of( new Location( row, col ) );
                }
            }
        }

        return Optional.empty();
    }

    public Optional<Location> locationOfCell( Cell cell ) {
        for ( int row = 0; row < ROW_CNT; row++ ) {
            for ( int col = 0; col < COL_CNT; col++ ) {
                if ( this.grid.get( row ).get( col ).equals( cell ) ) {
                    return Optional.of( new Location( row, col ) );
                }
            }
        }

        return Optional.empty();
    }

    private List<List<Cell>> copy( List<List<Cell>> grid ) {
        return Arrays.asList( grid.get( 0 ), grid.get( 1 ), grid.get( 2 ) );
    }

    private boolean validLocation( Location location ) {
        return location.getRow() <= ROW_CNT &&
                location.getRow() >= 0 &&
                location.getCol() <= COL_CNT &&
                location.getCol() >= 0;
    }
}
