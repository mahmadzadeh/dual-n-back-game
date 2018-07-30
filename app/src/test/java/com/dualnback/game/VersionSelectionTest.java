package com.dualnback.game;


import com.dualnback.dao.DataDto;
import com.dualnback.dao.DataPoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static com.dualnback.game.NBackVersion.FiveBack;
import static com.dualnback.game.NBackVersion.FourBack;
import static com.dualnback.game.NBackVersion.ThreeBack;
import static com.dualnback.game.NBackVersion.TwoBack;
import static com.dualnback.game.VersionSelection.MIN_REQUIRED_SC0RE_TO_GO_TO_NEXT_LVL;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class VersionSelectionTest {

    private static final int HIGHEST_SCORE = 60;

    private static final NBackVersion TEST_VERSION = NBackVersion.TwoBack;

    private DataDto dataDto;

    private VersionSelection versionSelection;


    @Test
    public void givenNoPreviousGamesPlayedThenLevelDefaultsToTwo( ) {
        versionSelection = new VersionSelection( testDto( 0 ) );

        assertThat( versionSelection.currentLevel() ).isEqualTo( NBackVersion.TwoBack );
    }

    @Test
    public void givenOnePreviousGameThenItsScoreIsUsedToDetermineNextGameLevel( ) {
        versionSelection = new VersionSelection( testDto( 1 ) );

        assertThat( versionSelection.currentLevel() ).isEqualTo( TwoBack );
    }

    @Test
    public void givenOnePreviousGameWhenScoreHighNextGameLevelIsIncremented( ) {
        versionSelection = new VersionSelection( testDto( 1, MIN_REQUIRED_SC0RE_TO_GO_TO_NEXT_LVL ) );

        assertThat( versionSelection.currentLevel() ).isEqualTo( ThreeBack );
    }

    @Test
    public void givenMultiplePreviousGameWhenScoreHighNextGameLevelIsIncremented( ) {
        versionSelection = new VersionSelection( testDto( 10, MIN_REQUIRED_SC0RE_TO_GO_TO_NEXT_LVL ) );

        assertThat( versionSelection.currentLevel() ).isEqualTo( ThreeBack );
    }

    @Test
    public void givenTwoGamesWhenLastGameScoreSmallerThanMinRequiredScoreThenLastGameVersionUsed( ) {
        Date today = new Date();

        Calendar instance = Calendar.getInstance();
        instance.add( Calendar.DAY_OF_MONTH, -1 );

        Date yesterday = instance.getTime();

        DataDto dataDto = new DataDto(
                Arrays.asList(
                        createDataPoint( yesterday, 90, FourBack ),
                        createDataPoint( today, 66, FiveBack )
                ) );

        versionSelection = new VersionSelection( dataDto );

        assertThat( versionSelection.currentLevel() ).isEqualTo( FiveBack );
    }

    private DataDto testDto( int size ) {

        return testDto( size, HIGHEST_SCORE );
    }

    private DataDto testDto( int size, int highestScore ) {

        List<DataPoint> userDataPoints = getRandomDataPoints( size, highestScore );

        return new DataDto( userDataPoints );
    }

    private List<DataPoint> getRandomDataPoints( int count, int highestScore ) {
        return IntStream
                .range( 0, count )
                .mapToObj( i -> createDataPoint( new Date(), highestScore, TEST_VERSION ) )
                .collect( ArrayList::new, ArrayList::add, ArrayList::addAll );
    }

    private DataPoint createDataPoint( Date date, int score, NBackVersion version ) {
        return new DataPoint( date, score, version );
    }

}
