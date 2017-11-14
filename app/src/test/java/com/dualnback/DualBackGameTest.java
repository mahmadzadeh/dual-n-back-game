package com.dualnback;

import com.dualnback.location.Location;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.ASound;
import com.dualnback.sound.BSound;
import com.dualnback.sound.Sound;
import com.dualnback.sound.SoundCollection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.dualnback.UserInputEvaluation.CorrectLocation;
import static com.dualnback.UserInputEvaluation.CorrectSound;
import static com.dualnback.UserInputEvaluation.IncorrectLocation;
import static com.dualnback.UserInputEvaluation.IncorrectSound;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

        SoundLocation newSoundAndLoc = addItemToHistory();

        assertEquals( 1, subjectUnderTest.getCopyOfHistory().size() );
    }

    @Test
    public void given1NBackWhenNothingInHistoryThenEvaluateSoundInputReturnsIncorrectSound( ) {

        UserInputEvaluation result = subjectUnderTest.evaluateSound( testSound );

        assertEquals( IncorrectSound, result );
    }

    @Test
    public void given1NBackWhenNothingInHistoryThenEvaluateLocationInputReturnsIncorrectLocation( ) {
        Location userInput = new Location( 0, 0 );

        UserInputEvaluation result = subjectUnderTest.evaluateLocation( userInput );

        assertEquals( IncorrectLocation, result );
    }

    /**
     * correct sound
     */
    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectSoundGivenThenEvaluateSoundInputReturnsCorrectSound( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        Sound userInput = addItemToHistory().getSound();

        UserInputEvaluation result = subjectUnderTest.evaluateSound( userInput );

        assertExpectedValues( result, CorrectSound, 0.00 );
    }

    /**
     * correct location
     */
    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectLocationGivenThenEvaluateUserInputReturnsCorrectSoundAndCorrectLocation( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        Location userInput = addItemToHistory().getLocation();

        UserInputEvaluation result = subjectUnderTest.evaluateLocation( userInput );

        assertExpectedValues( result, CorrectLocation, 0.00 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndIncorrectSoundGivenThenEvaluateUserInputReturnsIncorrectSound( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        UserInputEvaluation result = subjectUnderTest.evaluateSound( new BSound( 222 ) );

        UserInputEvaluation expectedResult = IncorrectSound;
        double expectedScorePercentage = 0.00;

        assertExpectedValues( result, expectedResult, expectedScorePercentage );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndIncorrectLocationGivenThenEvaluateUserInputReturnsIncorrectLocation( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        UserInputEvaluation result = subjectUnderTest.evaluateLocation( new Location( 100, 100 ) );

        assertExpectedValues( result, IncorrectLocation, 0.00 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndIncorrectSoundGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        UserInputEvaluation result = subjectUnderTest.evaluateSound( new BSound( 222 ) );

        subjectUnderTest.markEndOfOneRound();

        assertExpectedValues( result, IncorrectSound, 0.00 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndIncorrectLocationGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        UserInputEvaluation result = subjectUnderTest.evaluateLocation( new Location( 100, 100 ) );

        subjectUnderTest.markEndOfOneRound();

        assertExpectedValues( result, IncorrectLocation, 0.00 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectSoundGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        Sound correctSoundInput = addItemToHistory().getSound();

        UserInputEvaluation result = subjectUnderTest.evaluateSound( correctSoundInput );
        subjectUnderTest.markEndOfOneRound();

        // only 50% score since only a correct sound guess was recorded for the round
        assertExpectedValues( result, CorrectSound, 50.0 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectLocationGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        Location correctSoundInput = addItemToHistory().getLocation();

        UserInputEvaluation result = subjectUnderTest.evaluateLocation( correctSoundInput );
        subjectUnderTest.markEndOfOneRound();

        assertExpectedValues( result, CorrectLocation, 50.0 );
    }

    @Test
    public void given1NBackWhenOneItemHistoryAndCorrectLocationAndSoundGivenWhenRoundEndsThenUpdateScoreReflectTheResult( ) {

        setupMockSoundAndLocation( testSound, testLocation );

        SoundLocation soundLocation = addItemToHistory();

        Location correctLocInput = soundLocation.getLocation();
        Sound correctSound = soundLocation.getSound();

        UserInputEvaluation soundResult = subjectUnderTest.evaluateSound( correctSound );
        UserInputEvaluation locationResult = subjectUnderTest.evaluateLocation( correctLocInput );

        subjectUnderTest.markEndOfOneRound();

        // score is 100% since sound and location are both correctly guessed
        assertExpectedValues( locationResult, CorrectLocation, 100 );
        assertExpectedValues( soundResult, CorrectSound, 100 );
    }

    private void setupMockSoundAndLocation( ASound testSound, Location testLocation ) {
        when( mockSoundCollection.getRandomSound() ).thenReturn( testSound );
        when( mockLocationCollection.getRandomLocation() ).thenReturn( testLocation );
    }

    private SoundLocation addItemToHistory( ) {
        SoundLocation newSoundAndLoc = subjectUnderTest.getRandomSoundAndLocation( mockSoundCollection, mockLocationCollection );
        subjectUnderTest.storeInHistory( newSoundAndLoc );
        return newSoundAndLoc;
    }

    private void assertExpectedValues( UserInputEvaluation result, UserInputEvaluation expectedResult, double expectedScorePercentage ) {
        assertEquals( expectedResult, result );
        assertEquals( expectedScorePercentage, subjectUnderTest.getScorePercentage(), 0.001 );
    }


}