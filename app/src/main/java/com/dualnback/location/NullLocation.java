package com.dualnback.location;

public final class NullLocation extends Location {
    private static NullLocation instance;

    private NullLocation( int col, int row ) {
        super( row, col );
    }

    public static NullLocation INSTANCE( ) {

        if ( instance == null ) {
            instance = new NullLocation( -1, -1 );
        }

        return instance;
    }
}
