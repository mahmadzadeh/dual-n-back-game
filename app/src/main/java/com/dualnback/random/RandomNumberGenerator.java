package com.dualnback.random;

import com.dualnback.game.IntegerRange;

import java.util.Random;


public class RandomNumberGenerator {

    private final static Random random = new Random( System.currentTimeMillis() );

    public static Integer next( IntegerRange range ) {
        return random.nextInt( range.upperBound() - range.lowerBound() + 1 ) + range.lowerBound();
    }
}

