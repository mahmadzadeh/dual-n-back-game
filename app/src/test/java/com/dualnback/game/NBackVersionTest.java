package com.dualnback.game;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class NBackVersionTest {

    @Mock
    private Context mockContext;

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

    @Test
    public void givenCurrentVersionThenPreviousVersionDownReturnsCorrectValue( ) {

        Map<NBackVersion, Optional<NBackVersion>> expected = new HashMap<>();
        expected.put( Null, Optional.empty() );
        expected.put( OneBack, Optional.empty() );
        expected.put( TwoBack, Optional.of( OneBack ) );
        expected.put( ThreeBack, Optional.of( TwoBack ) );
        expected.put( FourBack, Optional.of( ThreeBack ) );
        expected.put( FiveBack, Optional.of( FourBack ) );
        expected.put( SixBack, Optional.of( FiveBack ) );
        expected.put( SevenBack, Optional.of( SixBack ) );
        expected.put( EightBack, Optional.of( SevenBack ) );
        expected.put( NineBack, Optional.of( EightBack ) );

        for ( NBackVersion version : NBackVersion.values() ) {
            assertThat( version.previousVersionDown() ).isEqualTo( expected.get( version ) );
        }
    }

    @Test
    public void givenCurrentVersionThenMapMethodWillSelectIndexOfArrayAdapter( ) {

        List<CharSequence> arrayAdapter = new ArrayList<>();

        stream( NBackVersion.values() )
                .forEach( ver -> arrayAdapter.add( ver.getTextRepresentation() ) );

        assertThat( OneBack.map( arrayAdapter ) ).isEqualTo( 1 );
        assertThat( TwoBack.map( arrayAdapter ) ).isEqualTo( 2 );
        assertThat( NineBack.map( arrayAdapter ) ).isEqualTo( 9 );
    }


}