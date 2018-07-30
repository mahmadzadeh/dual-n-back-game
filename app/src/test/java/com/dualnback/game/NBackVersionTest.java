package com.dualnback.game;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.dualnback.game.NBackVersion.EightBack;
import static com.dualnback.game.NBackVersion.FiveBack;
import static com.dualnback.game.NBackVersion.FourBack;
import static com.dualnback.game.NBackVersion.NineBack;
import static com.dualnback.game.NBackVersion.Null;
import static com.dualnback.game.NBackVersion.OneBack;
import static com.dualnback.game.NBackVersion.SevenBack;
import static com.dualnback.game.NBackVersion.SixBack;
import static com.dualnback.game.NBackVersion.ThreeBack;
import static com.dualnback.game.NBackVersion.TwoBack;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class NBackVersionTest {


    @Test
    public void givenInvalidUiRepresentationOfNBackVersionThenFromUiReturnsOptionalEmpty( ) throws Exception {
        assertEquals( Optional.empty(), NBackVersion.fromTextValue( "bugus_version" ) );
    }

    @Test
    public void givenValidUiRepresentationOfNBackVersionThenFromUiReturnsIt( ) throws Exception {

        asList( "Dual 2-Back", "Dual 3-Back", "Dual 4-Back", "Dual 5-Back", "Dual 6-Back", "Dual 7-Back", "Dual 8-Back", "Dual 9-Back" )
                .stream()
                .forEach( ui -> {
                    assertTrue( NBackVersion.fromTextValue( ui ).get() instanceof NBackVersion );
                } );
    }

    @Test
    public void givenCurrentVersionThenNextVersionUpReturnsCorrectValue( ) {

        Map<NBackVersion, Optional<NBackVersion>> expected = new HashMap<>();
        expected.put( Null, Optional.empty() );
        expected.put( OneBack, Optional.of( TwoBack ) );
        expected.put( TwoBack, Optional.of( ThreeBack ) );
        expected.put( ThreeBack, Optional.of( FourBack ) );
        expected.put( FourBack, Optional.of( FiveBack ) );
        expected.put( FiveBack, Optional.of( SixBack ) );
        expected.put( SixBack, Optional.of( SevenBack ) );
        expected.put( SevenBack, Optional.of( EightBack ) );
        expected.put( EightBack, Optional.of( NineBack ) );
        expected.put( NineBack, Optional.empty() );

        for ( NBackVersion version : NBackVersion.values() ) {
            assertThat( version.nextVersionUp() ).isEqualTo( expected.get( version ) );
        }
    }


}