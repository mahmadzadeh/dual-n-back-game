package com.dualnback.game;

import com.dualnback.location.Location;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.ASound;
import com.dualnback.sound.Sound;
import com.dualnback.sound.SoundCollection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.dualnback.game.UserInputEvaluation.CorrectLocation;
import static com.dualnback.game.UserInputEvaluation.CorrectSound;
import static com.dualnback.game.UserInputEvaluation.IncorrectLocation;
import static com.dualnback.game.UserInputEvaluation.IncorrectSound;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class DualBackGameTest {

    private final ASound testSound = new ASound( 111 );
    private final Location testLocation = new Location( 0, 0 );

    @Mock
    Sound sound;

    @Mock
    Location mockLocation;

    @Mock
    private SoundCollection mockSoundCollection;

    @Mock
    private LocationCollection mockLocationCollection;
    private DualBackGame subjectUnderTest;

    @Mock
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

        setupMockSoundAndLocation( testSound, testLocation );

        SoundLocation newSoundAndLoc = subjectUnderTest.getRandomSoundAndLocation( mockSoundCollection, mockLocationCollection );

        assertNotNull( newSoundAndLoc.getSound() );
        assertNotNull( newSoundAndLoc.getLocation() );
    }

    @Test
    public void givenInstanceThenCanStoreSoundAndLocationInHistory( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        SoundLocation newSoundAndLoc = getRandomSoundAndLocation();

        subjectUnderTest.updateGame( newSoundAndLoc );

        assertEquals( 1, subjectUnderTest.getCopyOfHistory().size() );
    }

    @Test
    public void given1NBackWhenNothingInHistoryThenEvaluateSoundInputReturnsIncorrectSound( ) {

        UserInputEvaluation result = subjectUnderTest.evaluateSound();

        assertEquals( IncorrectSound, result );
    }

    @Test
    public void given1NBackWhenNothingInHistoryThenEvaluateLocationInputReturnsIncorrectLocation( ) {
        Location userInput = new Location( 0, 0 );

        UserInputEvaluation result = subjectUnderTest.evaluateLocation();

        assertEquals( IncorrectLocation, result );
    }

    /**
     * correct sound
     */
    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectSoundGivenThenEvaluateSoundInputReturnsCorrectSound( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        SoundLocation correctSoundInput = getRandomSoundAndLocation();

        doNothing().when( dualBackGrid ).updateGridAtLocation( correctSoundInput.getLocation() );

        subjectUnderTest.updateGame( correctSoundInput );

        UserInputEvaluation result = subjectUnderTest.evaluateSound();

        verify( dualBackGrid ).updateGridAtLocation( correctSoundInput.getLocation() );
        assertExpectedValues( result, CorrectSound, 0.00 );
    }

    /**
     * correct location
     */
    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectLocationGivenThenEvaluateUserInputReturnsCorrectSoundAndCorrectLocation( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        SoundLocation correctSoundInput = getRandomSoundAndLocation();

        doNothing().when( dualBackGrid ).updateGridAtLocation( correctSoundInput.getLocation() );

        subjectUnderTest.updateGame( correctSoundInput );

        UserInputEvaluation result = subjectUnderTest.evaluateLocation();

        assertExpectedValues( result, CorrectLocation, 0.00 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndIncorrectSoundGivenThenEvaluateUserInputReturnsIncorrectSound( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        UserInputEvaluation result = subjectUnderTest.evaluateSound();

        UserInputEvaluation expectedResult = IncorrectSound;
        double expectedScorePercentage = 0.00;

        assertExpectedValues( result, expectedResult, expectedScorePercentage );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndIncorrectLocationGivenThenEvaluateUserInputReturnsIncorrectLocation( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        UserInputEvaluation result = subjectUnderTest.evaluateLocation();

        assertExpectedValues( result, IncorrectLocation, 0.00 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndIncorrectSoundGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        UserInputEvaluation result = subjectUnderTest.evaluateSound();

        subjectUnderTest.markEndOfOneRound();

        assertExpectedValues( result, IncorrectSound, 0.00 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndIncorrectLocationGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        UserInputEvaluation result = subjectUnderTest.evaluateLocation();

        subjectUnderTest.markEndOfOneRound();

        assertExpectedValues( result, IncorrectLocation, 0.00 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectSoundGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        SoundLocation correctSoundInput = getRandomSoundAndLocation();

        doNothing().when( dualBackGrid ).updateGridAtLocation( correctSoundInput.getLocation() );

        subjectUnderTest.updateGame( correctSoundInput );

        UserInputEvaluation result = subjectUnderTest.evaluateSound();

        subjectUnderTest.markEndOfOneRound();

        verify( dualBackGrid ).updateGridAtLocation( correctSoundInput.getLocation() );

        // only 50% score since only a correct sound guess was recorded for the round
        assertExpectedValues( result, CorrectSound, 50.0 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectLocationGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        SoundLocation correctSoundInput = getRandomSoundAndLocation();

        doNothing().when( dualBackGrid ).updateGridAtLocation( correctSoundInput.getLocation() );

        subjectUnderTest.updateGame( correctSoundInput );

        UserInputEvaluation result = subjectUnderTest.evaluateLocation();

        subjectUnderTest.markEndOfOneRound();

        verify( dualBackGrid ).updateGridAtLocation( correctSoundInput.getLocation() );

        assertExpectedValues( result, CorrectLocation, 50.0 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectLocationAndSoundGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        SoundLocation soundLocation = getRandomSoundAndLocation();

        doNothing().when( dualBackGrid ).updateGridAtLocation( soundLocation.getLocation() );

        subjectUnderTest.updateGame( soundLocation );

        UserInputEvaluation soundResult = subjectUnderTest.evaluateSound();
        UserInputEvaluation locationResult = subjectUnderTest.evaluateLocation();

        subjectUnderTest.markEndOfOneRound();

        verify( dualBackGrid ).updateGridAtLocation( soundLocation.getLocation() );

        // score is 100% since sound and location are both correctly guessed
        assertExpectedValues( locationResult, CorrectLocation, 100 );
        assertExpectedValues( soundResult, CorrectSound, 100 );
    }

    @Test
    public void givenRandomSoundAndLocationThenImageAtLocationIsSet( ) {
        setupMockSoundAndLocation( testSound, testLocation );

        SoundLocation soundLocation = getRandomSoundAndLocation();

//        subjectUnderTest.

    }

    private void setupMockSoundAndLocation( ASound testSound, Location testLocation ) {
        when( mockSoundCollection.getRandomSound() ).thenReturn( testSound );
        when( mockLocationCollection.getRandomLocation() ).thenReturn( testLocation );
    }

    private SoundLocation getRandomSoundAndLocation( ) {
        return subjectUnderTest.getRandomSoundAndLocation( mockSoundCollection, mockLocationCollection );
    }

    private void assertExpectedValues( UserInputEvaluation result, UserInputEvaluation expectedResult, double expectedScorePercentage ) {
        assertEquals( expectedResult, result );
        assertEquals( expectedScorePercentage, subjectUnderTest.getScorePercentage(), 0.001 );
    }


}