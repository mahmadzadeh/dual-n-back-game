package com.dualnback.game;


public enum NBackVersion {

    OneBack( 1 ),
    TwoBack( 2 ),
    ThreeBack( 3 ),
    FourBack( 4 ),
    FiveBack( 5 );

    int howFarBack;

    NBackVersion( int i ) {
        howFarBack = i;
    }
}
