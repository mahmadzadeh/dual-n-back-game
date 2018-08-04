package com.dualnback.game;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.IntStream.range;

public enum NBackVersion {

    Null( -1, "Missing Version" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return empty();
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return empty();
        }
    },
    OneBack( 1, "Dual 1-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( TwoBack );
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return empty();
        }
    },
    TwoBack( 2, "Dual 2-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( ThreeBack );
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return of( OneBack );
        }
    },
    ThreeBack( 3, "Dual 3-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( FourBack );
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return of( TwoBack );
        }
    },
    FourBack( 4, "Dual 4-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( FiveBack );
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return of( ThreeBack );
        }
    },
    FiveBack( 5, "Dual 5-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( SixBack );
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return of( FourBack );
        }
    },
    SixBack( 6, "Dual 6-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( SevenBack );
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return of( FiveBack );
        }
    },
    SevenBack( 7, "Dual 7-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( EightBack );
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return of( SixBack );
        }
    },
    EightBack( 8, "Dual 8-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return of( NineBack );
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return of( SevenBack );
        }
    },
    NineBack( 9, "Dual 9-Back" ) {
        @Override
        public Optional<NBackVersion> nextVersionUp( ) {
            return empty();
        }

        @Override
        public Optional<NBackVersion> previousVersionDown( ) {
            return of( EightBack );
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

    public int map( List<CharSequence> adapter ) {
        return range( 0, adapter.size() )
                .filter( index -> adapter.get( index ).toString().equals( getTextRepresentation() ) )
                .findFirst()
                .orElseThrow( ( ) ->
                        new RuntimeException( "Unable to locate the version in list of version" ) );
    }

    public String getTextRepresentation( ) {
        return textRepresentation;
    }

    public int getHowFarBack( ) {
        return howFarBack;
    }

    public abstract Optional<NBackVersion> nextVersionUp( );

    public abstract Optional<NBackVersion> previousVersionDown( );
}
