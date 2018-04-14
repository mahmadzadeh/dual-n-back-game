package com.dualnback.game;

import com.dualnback.SwappableImage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class CellTest {

    @Mock
    SwappableImage mockImageView;

    Cell cell;

    @Before
    public void setUp( ) {
        initMocks( this );

        cell = new Cell( 0, 1, mockImageView );
    }

    @Test
    public void aCellAlwaysStartsWithImageBackgroundImageUnset( ) {

        assertFalse( cell.isTurnedOn() );
    }

    @Test
    public void canTurnOffACell( ) {

        cell.turnOff();

        assertFalse( cell.isTurnedOn() );
    }

    @Test
    public void canTurnOnACell( ) {
        cell.turnOn();

        assertTrue( cell.isTurnedOn() );
    }
}