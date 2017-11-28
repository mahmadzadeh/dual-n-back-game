package com.dualnback.random;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RandomBooleanTest {

    @Test
    public void nextRandomTrueAlwaysTrue( ) throws Exception {

        Boolean certainlyTrue = RandomBoolean.nextRandomTrueWithOneOutOfNChance( 1 );

        assertTrue( certainlyTrue );
    }
}