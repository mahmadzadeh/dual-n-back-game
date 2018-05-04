package com.dualnback.game;


import java.util.Arrays;
import java.util.Optional;

public enum NBackVersion {

    OneBack( 1, "1-Back" ),
    TwoBack( 2, "2-Back" ),
    ThreeBack( 3, "3-Back" ),
    FourBack( 4, "4-Back" ),
    FiveBack( 5, "5-Back" ),
    SixBack( 6, "6-Back" ),
    SevenBack( 7, "7-Back" ),
    EightBack( 8, "8-Back" ),
    NineBack( 9, "9-Back" );

    private final String textRepresentation;

    private final int howFarBack;

    NBackVersion( int i, String textRepresentation ) {
        howFarBack = i;
        this.textRepresentation = textRepresentation;
    }

    public static Optional<NBackVersion> fromUiValue( String uiString ) {

        return Arrays.stream( NBackVersion.values() )
                .filter( v -> v.textRepresentation.equals( uiString ) )
                .findFirst();
    }

    public String getTextRepresentation( ) {
        return textRepresentation;
    }

    public int getHowFarBack( ) {
        return howFarBack;
    }
}
