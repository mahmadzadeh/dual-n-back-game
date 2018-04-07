package com.dualnback.game;

import android.support.annotation.NonNull;

import com.dualnback.location.Location;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DualBackGridTest {

    int onImageId = 0;
    int offImageId = 1;

    Cell onCell = new Cell( 1, 2 );
    Cell offCell = new Cell( 1, 2 );

    List<Cell> row1 = asList(
            new Cell( onImageId, offImageId ),
            new Cell( onImageId, offImageId ),
            new Cell( onImageId, offImageId ) );

    List<Cell> row2 = asList(
            new Cell( onImageId, offImageId ),
            new Cell( onImageId, offImageId ),
            new Cell( onImageId, offImageId ) );

    List<Cell> row3 = asList(
            new Cell( onImageId, offImageId ),
            new Cell( onImageId, offImageId ),
            new Cell( onImageId, offImageId ) );

    List<List<Cell>> grid = new ArrayList<List<Cell>>() {{
        add( row1 );
        add( row2 );
        add( row3 );
    }};

    AlternativeDualBackGrid subjectUnderTest;

    @Before
    public void setUp( ) {
        onCell.turnOn();

        subjectUnderTest = new AlternativeDualBackGrid( grid );
    }

//    @Test
//    public void givenGridOfCellsWhenASoundAndLocationGivenThenGridCellAtLocationIsSet( ) {
//
//        SoundLocation randomSoundLocation = new SoundLocation( new ASound( 111 ), new Location( 0, 0 ) );
//
//        subjectUnderTest.updateGridAtLocation( randomSoundLocation.getLocation() );
//
//        Location turnOnCellLocation = subjectUnderTest.getTurnedOnCell();
//
//        assertEquals( turnOnCellLocation, randomSoundLocation.getLocation() );
//    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidGridThenConstructorThrowsException( ) {
        new AlternativeDualBackGrid( null );
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenMalformedGridThenConstructorThrowsException( ) {
        Cell onCell = new Cell( 1, 2 );
        onCell.turnOn();
        Cell offCell = new Cell( 1, 2 );

        new AlternativeDualBackGrid( Arrays.asList( buildOneRowWithOneOnCellAnd( onCell, offCell ) ) );
    }

    @Test
    public void givenGridWhenNoCellOnThenGetCurrentTurnedOnCellReturnsOptionalEmpty( ) {

        assertEquals( Optional.empty(), subjectUnderTest.getTurnedOnCell() );
    }

    @Test
    public void givenGridWhenOneCellOnThenGetCurrentTurnedOnCellReturnsIt( ) {

        grid = getGrid( onCell, offCell );

        subjectUnderTest = new AlternativeDualBackGrid( grid );

        Optional<Cell> turnedOnCell = subjectUnderTest.getTurnedOnCell();

        assertTrue( turnedOnCell.isPresent() );
        assertEquals( onCell, turnedOnCell.get() );
    }

    @Test
    public void givenLocationThenGridCanBeUpdatedAtLocation( ) {

        Location location = new Location( 0, 0 );

        grid = getGrid( offCell, offCell );

        subjectUnderTest = new AlternativeDualBackGrid( grid );

        assertEquals( Optional.empty(), subjectUnderTest.getTurnedOnCell() );

        Cell cell = subjectUnderTest.turnOnCellAtLocation( location );

        assertTrue( cell.isTurnedOn() );
    }

    @Test
    public void givenGridContainsTurnedOnCellThenGetTurnedOnCellLocationReturnsItsLocation( ) {
        grid = getGrid( onCell, offCell );

        subjectUnderTest = new AlternativeDualBackGrid( grid );

        assertTrue( subjectUnderTest.getTurnedOnCell().isPresent() );


        Optional<Location> location = subjectUnderTest.getLocationOfTurnedOnCell();

        assertTrue( location.isPresent() );

        assertEquals( new Location( 0, 0 ), location.get() );
    }


    @NonNull
    private List<List<Cell>> getGrid( Cell onCell, Cell offCell ) {
        return buildGrid(
                buildOneRowWithOneOnCellAnd( onCell, offCell ),
                buildOneRowWithOneOnCellAnd( offCell, offCell ),
                buildOneRowWithOneOnCellAnd( offCell, offCell ) );
    }

    private List<Cell> buildOneRowWithOneOnCellAnd( Cell onCell, Cell otherCells ) {
        return Arrays.asList( onCell, otherCells, otherCells );
    }

    private List<List<Cell>> buildGrid( List<Cell> row1, List<Cell> row2, List<Cell> row3 ) {
        return Arrays.asList( row1, row2, row3 );
    }

    private class AlternativeDualBackGrid {
        List<List<Cell>> grid;

        public AlternativeDualBackGrid( List<List<Cell>> grid ) {
            if ( grid == null || grid.size() < 3 ) {
                throw new IllegalArgumentException( "Invalid Grid Given. Need a grid of 3X3" );
            }

            this.grid = copy( grid );
        }

        private List<List<Cell>> copy( List<List<Cell>> grid ) {
            List<Cell> toCopyInto = grid.get( 0 );
            List<Cell> toCopyInto1 = grid.get( 1 );
            List<Cell> toCopyInto3 = grid.get( 2 );

            return Arrays.asList( toCopyInto, toCopyInto1, toCopyInto3 );

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
            Cell cell = grid
                    .get( location.getRow() )
                    .get( location.getCol() );

            cell.turnOn();

            return cell;
        }

        public Optional<Location> getLocationOfTurnedOnCell( ) {
            for ( int row = 0; row < 3; row++ ) {
                for ( int col = 0; col < 3; col++ ) {
                    if ( this.grid.get( row ).get( col ).isTurnedOn() ) {
                        return Optional.of( new Location( row, col ) );
                    }
                }
            }

            return Optional.empty();
        }
    }
}