package com.dualnback;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DualBackGameTest {

    private DualBackGame subjectUnderTest;
    private DualBackGrid dualBackGrid;

    @Before
    public void setUp( ) {
        subjectUnderTest = new DualBackGame( dualBackGrid, NBackVersion.OneBack );
    }

    @Test
    public void addition_isCorrect( ) throws Exception {
        assertEquals( 4, 2 + 2 );

    }

}