package com.dualnback.game;

import android.support.annotation.NonNull;

import com.dualnback.location.Location;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DualBackGridTest {

    int onImageId = 0;
    int offImageId = 1;

    Cell onCell = new Cell( 1, 2 );
    Cell offCell = new Cell( 1, 2 );

    List<List<Cell>> grid;

    AlternativeDualBackGrid subjectUnderTest;

    @Before
    public void setUp( ) {
        onCell.turnOn();
    }

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
        grid = getGrid( offCell, offCell );
        subjectUnderTest = new AlternativeDualBackGrid( grid );

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

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidLocationToTurnOnTheCellThenTurnOnCellAtLocationThrowsException( ) {
        grid = getGrid( onCell, offCell );

        subjectUnderTest = new AlternativeDualBackGrid( grid );

        subjectUnderTest.turnOnCellAtLocation( new Location( 333, 3333 ) );
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

}