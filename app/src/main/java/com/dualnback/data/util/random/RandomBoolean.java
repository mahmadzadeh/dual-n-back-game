package com.dualnback.data.util.random;

import com.dualnback.util.IntegerRange;

public class RandomBoolean {

    public static Boolean nextRandomTrueWithOneOutOfNChance( Integer n ) {
        return RandomNumberGenerator.next( new IntegerRange( 1, n ) ) == 1;
    }

    public static Boolean trueWithFiftyPercentChance( ) {
        return nextRandomTrueWithOneOutOfNChance( 2 );
    }

}
