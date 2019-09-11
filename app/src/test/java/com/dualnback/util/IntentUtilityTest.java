package com.dualnback.util;

import android.os.Bundle;

import com.dualnback.game.NBackVersion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.dualnback.ui.mainscreen.MainActivity.VERSION;
import static com.dualnback.util.IntentUtility.extractFromExtrasWithDefault;
import static com.dualnback.util.IntentUtility.extractGameVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntentUtilityTest {

    @Mock
    private Bundle mockBundle;


    @Test
    public void givenValidKeyThenExtractFromExtrasWithDefaultReturnsValue( ) throws Exception {
        String key = "key";
        String value = "value";

        when( mockBundle.get( key ) ).thenReturn( value );

        String actual = extractFromExtrasWithDefault( mockBundle, key, "default" );

        assertEquals( value, actual );
    }

    @Test
    public void givenInvalidKeyThenExtractFromExtrasWithDefaultReturnsDefault( ) throws Exception {
        String key = "key";
        String defaultValue = "default";

        when( mockBundle.get( key ) ).thenReturn( null );

        String actual = extractFromExtrasWithDefault( mockBundle, key, defaultValue );

        assertEquals( defaultValue, actual );
    }

    @Test
    public void givenNoBundlePresentThenExtractFromExtrasWithDefaultReturnsDefault( ) throws Exception {
        String key = "key";
        String defaultValue = "default";

        when( mockBundle.get( key ) ).thenReturn( null );

        String actual = extractFromExtrasWithDefault( null, key, defaultValue );

        assertEquals( defaultValue, actual );
    }

    @Test
    public void givenNoVersionThenExtractGameVersionReturnsNullVersion( ) {

        when( mockBundle.getString( VERSION ) ).thenReturn( null );

        assertThat( extractGameVersion( mockBundle ) ).isEqualTo( NBackVersion.Null );

        verify( mockBundle ).getString( VERSION );
    }

    @Test
    public void givenNoBundlePresentThenExtractGameVersionReturnsNullVersion( ) {

        assertThat( extractGameVersion( null ) ).isEqualTo( NBackVersion.Null );

    }

    @Test
    public void givenCorrectVersionInBundleThenExtractGameVersionReturnsIt( ) {

        NBackVersion expectedVersion = NBackVersion.TwoBack;

        when( mockBundle.getString( VERSION ) ).thenReturn( expectedVersion.getTextRepresentation() );

        assertThat( extractGameVersion( mockBundle ) ).isEqualTo( expectedVersion );
    }


}