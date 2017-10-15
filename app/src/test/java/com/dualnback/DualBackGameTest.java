package com.dualnback;

import com.dualnback.location.Location;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;
import com.dualnback.sound.SoundPlayer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.dualnback.UserInputEvaluation.IncorrectSound;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class DualBackGameTest {

    @Mock
    SoundPlayer mockSoundPlayer;
    @Mock
    Location mockLocation;
    @Mock
    private SoundCollection mockSoundCollection;
    @Mock
    private LocationCollection mockLocationCollection;

    private DualBackGame subjectUnderTest;
    private DualBackGrid dualBackGrid;

    @Before
    public void setUp( ) {
        initMocks( this );

        subjectUnderTest = new DualBackGame( dualBackGrid, NBackVersion.OneBack );
    }

    @Test
    public void addition_isCorrect( ) throws Exception {
        assertEquals( 4, 2 + 2 );

    }

    @Test
    public void givenInstanceThenCanGetRandomSoundAndLocation( ) {

        setUpMocks();

        SoundLocation newSoundAndLoc = subjectUnderTest.getRandomSoundAndLocation( mockSoundCollection, mockLocationCollection );

        assertNotNull( newSoundAndLoc.getSound() );
        assertNotNull( newSoundAndLoc.getLocation() );
    }

    @Test
    public void givenInstanceThenCanStoreSoundAndLocationInHistory( ) {

        setUpMocks();

        SoundLocation newSoundAndLoc = subjectUnderTest.getRandomSoundAndLocation( mockSoundCollection, mockLocationCollection );

        subjectUnderTest.storeInHistory( newSoundAndLoc );

        assertEquals( 1, subjectUnderTest.getCopyOfHistory().size() );
    }

    @Test
    public void given1NBackWhenNothingInHistoryThenEvaluateUserInputReturnsIncorrectSoundAndLocation( ) {
        SoundLocation userInput = new SoundLocation( mockSoundPlayer, new Location( 0, 0 ) );

        UserInputEvaluation result = subjectUnderTest.evaluateSound( userInput );

        assertEquals( IncorrectSound, result );
    }

    /**
     * correct sound
     */
    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectLocationGivenThenEvaluateUserInputReturnsIncorrectSoundAndCorrectLocation( ) {
        SoundLocation userInput = new SoundLocation( mockSoundPlayer, new Location( 0, 0 ) );

        UserInputEvaluation result = subjectUnderTest.evaluateSound( userInput );

        assertEquals( IncorrectSound, result );
    }


    private void setUpMocks( ) {
        when( mockSoundCollection.getRandomSoundPlayer() ).thenReturn( mockSoundPlayer );
        when( mockLocationCollection.getRandomLocation() ).thenReturn( mockLocation );
    }


}