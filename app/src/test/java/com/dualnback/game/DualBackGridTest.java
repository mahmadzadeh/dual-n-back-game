package com.dualnback.game;

import android.support.annotation.NonNull;

import com.dualnback.location.Location;
import com.dualnback.view.MainScreenView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DualBackGridTest {

    @Mock
    MainScreenView mockImgView;

    int onImageId = 0;
    int offImageId = 1;

    Cell onCell;
    Cell offCell;

    List<List<Cell>> grid;

    DualBackGrid sut;

    @Before
    public void setUp( ) {
        doNothing().when( mockImgView ).swapImage( any( Cell.class ), anyInt() );

        onCell = new Cell( 1, 2, mockImgView );
        offCell = new Cell( 1, 2, mockImgView );

        onCell.turnOn();
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidGridThenConstructorThrowsException( ) {
        new DualBackGrid( null );
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenMalformedGridThenConstructorThrowsException( ) {
        Cell onCell = new Cell( 1, 2, mockImgView );
        onCell.turnOn();
        Cell offCell = new Cell( 1, 2, mockImgView );

        new DualBackGrid( Arrays.asList( buildOneRowWithOneOnCellAnd( onCell, offCell ) ) );

        verify( mockImgView ).swapImage( any( Cell.class ), anyInt() );
    }

    @Test
    public void givenGridWhenNoCellOnThenGetCurrentTurnedOnCellReturnsOptionalEmpty( ) {
        grid = getGrid( offCell, offCell );
        sut = new DualBackGrid( grid );

        assertEquals( Optional.empty(), sut.getTurnedOnCell() );
    }

    @Test
    public void givenGridWhenOneCellOnThenGetCurrentTurnedOnCellReturnsIt( ) {

        grid = getGrid( onCell, offCell );

        sut = new DualBackGrid( grid );

        Optional<Cell> turnedOnCell = sut.getTurnedOnCell();

        assertTrue( turnedOnCell.isPresent() );
        assertEquals( onCell, turnedOnCell.get() );
    }

    @Test
    public void givenLocationThenGridCanBeUpdatedAtLocation( ) {

        Location location = new Location( 0, 0 );

        grid = getGrid( offCell, offCell );

        sut = new DualBackGrid( grid );

        assertEquals( Optional.empty(), sut.getTurnedOnCell() );

        Cell cell = sut.turnOnCellAtLocation( location );

        assertTrue( cell.isTurnedOn() );
    }

    @Test
    public void givenGridContainsTurnedOnCellThenGetTurnedOnCellLocationReturnsItsLocation( ) {
        grid = getGrid( onCell, offCell );

        sut = new DualBackGrid( grid );

        assertTrue( sut.getTurnedOnCell().isPresent() );


        Optional<Location> location = sut.getLocationOfTurnedOnCell();

        assertTrue( location.isPresent() );

        assertEquals( new Location( 0, 0 ), location.get() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidLocationToTurnOnTheCellThenTurnOnCellAtLocationThrowsException( ) {
        grid = getGrid( onCell, offCell );

        sut = new DualBackGrid( grid );

        sut.turnOnCellAtLocation( new Location( 333, 3333 ) );
    }

    @Test
    public void givenInvalidCellThenLocationOfCellReturnsOptionalEmpty( ) {
        Cell nonExistentCell = new Cell( 333, 444, mockImgView );

        grid = getGrid( onCell, offCell );

        sut = new DualBackGrid( grid );

        assertEquals( Optional.empty(), sut.locationOfCell( nonExistentCell ) );
    }


    @Test
    public void givenValidCellThenLocationOfCellReturnsIt( ) {
        grid = getGrid( onCell, offCell );

        sut = new DualBackGrid( grid );

        assertEquals( new Location( 0, 0 ), sut.locationOfCell( onCell ).get() );
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