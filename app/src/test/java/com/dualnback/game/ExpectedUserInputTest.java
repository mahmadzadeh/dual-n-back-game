package com.dualnback.game;

import org.junit.Test;

import static com.dualnback.game.UserInput.LocationMatch;
import static com.dualnback.game.UserInput.SoundMatch;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExpectedUserInputTest {

    @Test
    public void createInstance( ) {

        ExpectedUserInput input = new ExpectedUserInput( SoundMatch, LocationMatch );
    }

    @Test
    public void givenInvalidSoundMatchThenIsSoundMatchReturnsFalse( ) {

        ExpectedUserInput input = new ExpectedUserInput( null, LocationMatch );

        assertFalse( input.isSoundMatch( SoundMatch ) );
    }

    @Test
    public void givenInvalidLocationMatchThenIsSoundMatchReturnsFalse( ) {

        ExpectedUserInput input = new ExpectedUserInput( null, null );

        assertFalse( input.isLocationMatch( LocationMatch ) );
    }

    @Test
    public void givenValidLocationMatchThenIsLocationAndSoundMatchReturnTrue( ) {

        ExpectedUserInput input = new ExpectedUserInput( SoundMatch, LocationMatch );

        assertTrue( input.isLocationMatch( LocationMatch ) );
        assertTrue( input.isSoundMatch( SoundMatch ) );
    }


}