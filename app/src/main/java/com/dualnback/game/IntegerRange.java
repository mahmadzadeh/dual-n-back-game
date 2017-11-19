package com.dualnback.game;


import java.util.Collection;

public class IntegerRange {

    private final Integer lower;
    private final Integer upper;

    public IntegerRange( int lower, int upper ) {

        if ( lower > upper ) {
            throw new IllegalArgumentException( "lower bound of the range has to be smaller than upper: given " + lower );
        }

        this.lower = lower;
        this.upper = upper;
    }

    public static <T> IntegerRange instanceWithinCollectionSize( Collection<T> collection ) {
        return new IntegerRange( 0, ( collection.size() ) - 1 );
    }

    public Integer lowerBound( ) {
        return lower;
    }

    public Integer upperBound( ) {
        return upper;
    }
}
