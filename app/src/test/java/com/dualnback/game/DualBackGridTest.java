package com.dualnback.game;

import com.dualnback.location.Location;
import com.dualnback.sound.ASound;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;


public class DualBackGridTest {

    int onImageId = 0;
    int offImageId = 1;

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

    DualBackGrid subjectUnderTest;

    @Before
    public void setUp( ) {
        subjectUnderTest = new DualBackGrid( grid );
    }

    @Test
    public void givenGridOfCellsWhenASoundAndLocationGivenThenGridCellAtLocationIsSet( ) {

        SoundLocation randomSoundLocation = new SoundLocation( new ASound( 111 ), new Location( 0, 0 ) );

        subjectUnderTest.updateGridAtLocation( randomSoundLocation.getLocation() );

        Location turnOnCellLocation = subjectUnderTest.getTurnedOnCell();

        assertEquals( turnOnCellLocation, randomSoundLocation.getLocation() );
    }


}