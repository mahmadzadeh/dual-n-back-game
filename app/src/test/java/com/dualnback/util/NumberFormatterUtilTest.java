package com.dualnback.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class NumberFormatterUtilTest {


    @Test
    public void givenDoubleThenFormatScoreWillFormatToClosestInt( ) {
        assertEquals( "85%", NumberFormatterUtil.formatScore( 85.44444d ) );
        assertEquals( "86%", NumberFormatterUtil.formatScore( 85.5d ) );
        assertEquals( "86%", NumberFormatterUtil.formatScore( 85.6d ) );
    }

}