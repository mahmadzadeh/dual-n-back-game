package com.dualnback;

import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class CellTest {

    @Mock
    ImageView mockImageView;

    Cell cell;

    @Before
    public void setUp( ) {
        initMocks( this );

        cell = new Cell( 0, 1 );
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