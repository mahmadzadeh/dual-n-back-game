package com.dualnback.util;

import android.os.Bundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.dualnback.util.IntentUtility.extractFromExtrasWithDefault;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntentUtilityTest {

    @Mock
    Bundle mockBundle;


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

}