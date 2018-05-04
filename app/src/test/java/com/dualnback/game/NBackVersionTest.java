package com.dualnback.game;

import org.junit.Test;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class NBackVersionTest {


    @Test
    public void givenInvalidUiRepresentationOfNBackVersionThenFromUiReturnsOptionalEmpty( ) throws Exception {
        assertEquals( Optional.empty(), NBackVersion.fromUiValue( "bugus_version" ) );
    }

    @Test
    public void givenValidUiRepresentationOfNBackVersionThenFromUiReturnsIt( ) throws Exception {

        asList( "2-Back", "3-Back", "4-Back", "5-Back", "6-Back", "7-Back", "8-Back", "9-Back" )
                .stream()
                .forEach( ui -> {
                    assertTrue( NBackVersion.fromUiValue( ui ).get() instanceof NBackVersion );
                } );
    }


}