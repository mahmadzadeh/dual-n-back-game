package com.dualnback.data.util.random;

import com.dualnback.util.IntegerRange;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RandomNumberGeneratorTest {

    @Test
    public void givenRangeThenNextWillReturnRandomNumberInRange( ) {

        IntegerRange range = new IntegerRange( 0, 10 );

        Integer next = RandomNumberGenerator.next( range );

        assertTrue( next instanceof Integer );

        assertTrue( range.lowerBound() <= next && next <= range.upperBound() );
    }

    @Test
    public void givenRangeAndDesiredNumberOfDistinctIntsThenGenerateSetOfDistinctInts( ) {

        int lowerBound = 0;
        int upperBound = 10;

        IntegerRange range = new IntegerRange( lowerBound, upperBound );

        Set<Integer> setOfNumbers = RandomNumberGenerator.next_N_DistinctRandomIntsWithinRange( 5, range );

        assertEquals( 5, setOfNumbers.size() );

        setOfNumbers.stream().forEach( integer -> {
                    assertTrue( "expected " + integer + "to be >= than " + lowerBound,
                            integer >= lowerBound );
                    assertTrue( "expected " + integer + "to be <= than " + upperBound,
                            integer <= upperBound );
                }
        );
    }

}