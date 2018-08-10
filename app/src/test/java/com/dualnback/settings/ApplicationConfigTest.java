package com.dualnback.settings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.dualnback.settings.ApplicationConfig.SINGLE_TRIAL_LENGTH_KEY;
import static com.dualnback.settings.ApplicationConfig.TRIAL_LENGTH_DEFAULT_IN_SECONDS;
import static com.dualnback.settings.ApplicationConfig.VIBRATION_DEFAULT_MILLISECONDS;
import static com.dualnback.settings.ApplicationConfig.VIBRATION_DURATION_LIST_KEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ApplicationConfigTest {

    ApplicationConfig sut;
    @Mock
    private ConfigReader reader;

    @Before
    public void setUp( ) {

        sut = new ApplicationConfig( reader );
    }

    @Test
    public void vibrationLength( ) throws Exception {

        int expectedValue = 100;

        when( reader
                .getStringPreferenceWithCastToInt(
                        VIBRATION_DURATION_LIST_KEY, VIBRATION_DEFAULT_MILLISECONDS ) )
                .thenReturn( expectedValue );

        assertThat( sut.vibrationLength() ).isEqualTo( expectedValue );
    }

    @Test
    public void getMinScoreToAdvance( ) throws Exception {

    }

    @Test
    public void getMinScoreToFallback( ) throws Exception {
    }

    @Test
    public void singleRoundDuration( ) throws Exception {
        int expectedValue = 5;

        when( reader.getIntPreference( SINGLE_TRIAL_LENGTH_KEY, TRIAL_LENGTH_DEFAULT_IN_SECONDS ) )
                .thenReturn( expectedValue );

        assertThat( sut.singleTrialDurationInMillis() ).isEqualTo( expectedValue * 1000 );
    }

}