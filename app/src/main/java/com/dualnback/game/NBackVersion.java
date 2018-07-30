package com.dualnback.game;


import java.util.Arrays;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public enum NBackVersion {

    Null( -1, "Missing Version" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return empty();
        }
    },
    OneBack( 1, "Dual 1-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( TwoBack );
        }
    },
    TwoBack( 2, "Dual 2-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( ThreeBack );
        }
    },
    ThreeBack( 3, "Dual 3-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( FourBack );
        }
    },
    FourBack( 4, "Dual 4-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( FiveBack );
        }
    },
    FiveBack( 5, "Dual 5-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( SixBack );
        }
    },
    SixBack( 6, "Dual 6-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( SevenBack );
        }
    },
    SevenBack( 7, "Dual 7-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( EightBack );
        }
    },
    EightBack( 8, "Dual 8-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( NineBack );
        }
    },
    NineBack( 9, "Dual 9-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return empty();
        }
    };

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

    public abstract Optional<NBackVersion> nextVersionUp( );
}
