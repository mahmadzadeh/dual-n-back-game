package com.dualnback;

import com.dualnback.location.Location;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.ASound;
import com.dualnback.sound.Sound;
import com.dualnback.sound.SoundCollection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.dualnback.UserInputEvaluation.CorrectSound;
import static com.dualnback.UserInputEvaluation.IncorrectSound;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class DualBackGameTest {

    @Mock
    Sound sound;
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
        Sound userInput = new ASound( 111 );

        UserInputEvaluation result = subjectUnderTest.evaluateSound( userInput );

        assertEquals( IncorrectSound, result );
    }

    /**
     * correct sound
     */
    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectLocationGivenThenEvaluateUserInputReturnsIncorrectSoundAndCorrectLocation( ) {

        when( mockSoundCollection.getRandomSoundPlayer() ).thenReturn( new ASound( 111 ) );
        when( mockLocationCollection.getRandomLocation() ).thenReturn( new Location( 0, 0 ) );

        SoundLocation newSoundAndLoc = subjectUnderTest.getRandomSoundAndLocation( mockSoundCollection, mockLocationCollection );
        subjectUnderTest.storeInHistory( newSoundAndLoc );


        Sound userInput = newSoundAndLoc.getSound();

        UserInputEvaluation result = subjectUnderTest.evaluateSound( userInput );

        assertEquals( CorrectSound, result );

        assertEquals( 0.00, subjectUnderTest.getScore(), 0.001 );
    }


    private void setUpMocks( ) {
        when( mockSoundCollection.getRandomSoundPlayer() ).thenReturn( sound );
        when( mockLocationCollection.getRandomLocation() ).thenReturn( mockLocation );
    }


}