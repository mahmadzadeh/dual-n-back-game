package com.dualnback.game.factory;

import android.content.Context;

import com.dualnback.game.DualBackGame;
import com.dualnback.game.NBackVersion;
import com.dualnback.game.Trial;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;
import com.dualnback.view.MainScreenView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.dualnback.MainActivity.EXPECTED_LOC_MATCHES;
import static com.dualnback.MainActivity.EXPECTED_SOUND_MATCHES;
import static com.dualnback.MainActivity.TOTAL_TRIAL_COUNT;
import static com.dualnback.game.factory.DualBackGameFactory.create;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DualBackGameFactoryTest {


    @Mock
    private Context mockContext;

    @Mock
    private MainScreenView mockSwappableImage;


    /**
     * two instances of DualBackGame share the same game trial collection!
     *
     * @throws Exception
     */
    @Test
    public void testReproducingBug( ) throws Exception {

        GameParameters parameters = new GameParameters()
                .withVersion( NBackVersion.TwoBack )
                .withContext( mockSwappableImage )
                .withExpectedSoundMatches( EXPECTED_SOUND_MATCHES )
                .withExpectedLocationMatches( EXPECTED_LOC_MATCHES )
                .withLocationCollection( new LocationCollection() )
                .withSoundCollection( new SoundCollection( SoundCollectionFactory.instance( mockContext ) ) );

        DualBackGame dualBackGame = create( parameters );

        Trial trial = null;

        int trialCount = 0;

        while ( ( trial = dualBackGame.getNextTrial() ) != null ) {
            // iterate through all trials
            trialCount++;
        }

        assertThat( trialCount ).isEqualTo( TOTAL_TRIAL_COUNT );

        trialCount = 0;

        dualBackGame = create( parameters );

        while ( ( trial = dualBackGame.getNextTrial() ) != null ) {
            // iterate through all trials
            trialCount++;
        }

        assertThat( trialCount ).isEqualTo( TOTAL_TRIAL_COUNT );
    }

}