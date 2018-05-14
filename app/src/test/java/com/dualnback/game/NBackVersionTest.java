package com.dualnback.game;

import org.junit.Test;

import java.util.Optional;

import static java.util.Arrays.asList;
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


}