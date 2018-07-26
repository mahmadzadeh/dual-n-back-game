package com.dualnback.game;


import java.util.Arrays;
import java.util.Optional;

public enum NBackVersion {

    Null( -1, "Missing Version" ),
    OneBack( 1, "Dual 1-Back" ),
    TwoBack( 2, "Dual 2-Back" ),
    ThreeBack( 3, "Dual 3-Back" ),
    FourBack( 4, "Dual 4-Back" ),
    FiveBack( 5, "Dual 5-Back" ),
    SixBack( 6, "Dual 6-Back" ),
    SevenBack( 7, "Dual 7-Back" ),
    EightBack( 8, "Dual 8-Back" ),
    NineBack( 9, "Dual 9-Back" );

    private final String textRepresentation;

    private final int howFarBack;

    NBackVersion( int i, String textRepresentation ) {
        howFarBack = i;
        this.textRepresentation = textRepresentation;
    }

    public static Optional<NBackVersion> fromTextValue( String textValue ) {

        return Arrays.stream( NBackVersion.values() )
                .filter( v -> v.textRepresentation.equals( textValue ) )
                .findFirst();
    }

    public String getTextRepresentation( ) {
        return textRepresentation;
    }

    public int getHowFarBack( ) {
        return howFarBack;
    }
}
