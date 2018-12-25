package com.dualnback.game;


import com.dualnback.dao.DataPoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static com.dualnback.game.NBackVersion.NineBack;
import static com.dualnback.game.NBackVersion.OneBack;
import static com.dualnback.game.NBackVersion.TwoBack;
import static com.dualnback.game.VersionSelection.currentLevel;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class VersionSelectionTest {

    private static final int HIGHEST_SCORE = 60;

    private static final NBackVersion TEST_VERSION = NBackVersion.TwoBack;

    private static final int MIN_REQUIRED_SCORE_UP = 80;
    private static final int MIN_SCORE_TO_MAINTAIN = 50;

    @Test
    public void givenNoPreviousGamesPlayedThenLevelDefaultsToTwo( ) {
        assertThat( currentLevel( Optional.empty(), MIN_REQUIRED_SCORE_UP, MIN_SCORE_TO_MAINTAIN ) )
                .isEqualTo( NBackVersion.TwoBack );
    }

    @Test
    public void givenOnePreviousGameThenItsScoreIsUsedToDetermineNextGameLevel( ) {
        Optional<DataPoint> dataPoint = lastDataDto( 60, TEST_VERSION );

        assertThat( currentLevel( dataPoint, MIN_REQUIRED_SCORE_UP, MIN_SCORE_TO_MAINTAIN ) ).isEqualTo( TwoBack );
    }

    @Test
    public void givenPreviousGameWhenScoreHighEnoughThenNextGameLevelIsIncremented( ) {
        Optional<DataPoint> dataPoint = lastDataDto( MIN_REQUIRED_SCORE_UP + 1, TEST_VERSION );

        assertThat( currentLevel( dataPoint, MIN_REQUIRED_SCORE_UP, MIN_SCORE_TO_MAINTAIN ) )
                .isEqualTo( TEST_VERSION.nextVersionUp().get() );
    }

    @Test
    public void givenPreviousGameWhenScoreEqualToMaxThenVersionIsIncremented( ) {
        Optional<DataPoint> dataPoint = lastDataDto( MIN_REQUIRED_SCORE_UP, TEST_VERSION );

        assertThat( currentLevel( dataPoint, MIN_REQUIRED_SCORE_UP, MIN_SCORE_TO_MAINTAIN ) )
                .isEqualTo( TEST_VERSION.nextVersionUp().get() );
    }

    @Test
    public void givenPreviousGameWhenAtHighestLevelThenCurrentLvlStays( ) {
        Optional<DataPoint> dataPoint = lastDataDto( MIN_REQUIRED_SCORE_UP, NineBack );

        assertThat( currentLevel( dataPoint, MIN_REQUIRED_SCORE_UP, MIN_SCORE_TO_MAINTAIN ) ).isEqualTo( NineBack );
    }

    @Test
    public void givenPreviousGameWhenAtLowestLevelThenCurrentLvlStays( ) {
        Optional<DataPoint> dataPoint = lastDataDto( MIN_SCORE_TO_MAINTAIN - 1, OneBack );

        assertThat( currentLevel( dataPoint, MIN_REQUIRED_SCORE_UP, MIN_SCORE_TO_MAINTAIN ) ).isEqualTo( OneBack );
    }

    @Test
    public void givenOnePreviousGameWhenScoreLessThanMinThenVersionIsBumppedDown( ) {
        Optional<DataPoint> dataPoint = lastDataDto( MIN_SCORE_TO_MAINTAIN - 1, TEST_VERSION );

        assertThat( currentLevel( dataPoint, MIN_REQUIRED_SCORE_UP, MIN_SCORE_TO_MAINTAIN ) )
                .isEqualTo( TEST_VERSION.previousVersionDown().get() );
    }

    @Test
    public void givenOnePreviousGameWhenScoreEqualToMinThenVersionIsNotBumpedDown( ) {
        Optional<DataPoint> dataPoint = lastDataDto( MIN_SCORE_TO_MAINTAIN, TEST_VERSION );

        assertThat( currentLevel( dataPoint, MIN_REQUIRED_SCORE_UP, MIN_SCORE_TO_MAINTAIN ) )
                .isEqualTo( TEST_VERSION );
    }

    private Optional<DataPoint> lastDataDto( int highestScore, NBackVersion version ) {
        return Optional.of( createDataPoint( new Date(), highestScore, version ) );
    }

    private DataPoint createDataPoint( Date date, int score, NBackVersion version ) {
        return new DataPoint( date, score, version );
    }

}
