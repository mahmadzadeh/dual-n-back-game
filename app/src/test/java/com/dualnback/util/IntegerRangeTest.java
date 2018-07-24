package com.dualnback.util;

import org.junit.Test;


public class IntegerRangeTest {
    @Test(expected = IllegalArgumentException.class)
    public void lowerBoundIsAlwaysSmallerThanUpperBound( ) {

        new IntegerRange( 2, 1 );
    }

    @Test
    public void createInstance( ) {
        new IntegerRange( 1, 1 );
    }

}