package com.dualnback;

import com.dualnback.random.RandomNumberGenerator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class RandomNumberGeneratorTest {

    @Test
    public void givenArangeThenNextWillReturnRandomNumberInRange( ) {

        IntegerRange range = new IntegerRange( 0, 10 );

        Integer next = RandomNumberGenerator.next( range );

        assertTrue( next instanceof Integer );

        assertTrue( range.lowerBound() <= next && next <= range.upperBound() );
    }

}