package com.dualnback.util;

import android.os.Bundle;

import com.dualnback.data.filesystem.dao.DataPoint;
import com.dualnback.game.NBackVersion;
import com.dualnback.ui.continuescreen.ContinueScreenActivity;

import java.util.Date;
import java.util.Optional;

import static com.dualnback.ui.mainscreen.MainActivity.FINAL_SCORE;
import static com.dualnback.ui.mainscreen.MainActivity.VERSION;

public class IntentUtility {

    private static final float DEFAULT_SCORE_WHEN_MISSING_IN_BUNDLE = 0.00f;

    public static <T> T extractFromExtrasWithDefault( Bundle extras, String key, T defaultValue ) {
        T value = defaultValue;

        if ( extras != null ) {
            value = ( T ) extras.get( key );
        }

        return value != null ? value : defaultValue;
    }

    public static <T> Optional<T> extractFromIntentExtras( Bundle extras, String key ) {
        T value = null;

        if ( extras != null ) {
            value = ( T ) extras.get( key );
        }

        return Optional.ofNullable( value );
    }

    public static NBackVersion extractGameVersion( Bundle extras ) {

        NBackVersion version = null;

        if ( extras != null ) {
            String string = extras.getString( VERSION );

            version = NBackVersion.fromTextValue( string ).orElse( NBackVersion.Null );
        }

        return version != null ? version : NBackVersion.Null;
    }

    public static DataPoint extractDatePointFromExtras( Bundle extras ) {
        Float score = DEFAULT_SCORE_WHEN_MISSING_IN_BUNDLE;

        Date date = new Date();

        NBackVersion nBackVersion = null;

        if ( extras != null ) {
            String string = extras.getString( FINAL_SCORE );

            score = Float.valueOf( string );

            date = DateUtil.parse( extras.getString( ContinueScreenActivity.DATE ) );

            nBackVersion = extractGameVersion( extras );
        }

        return new DataPoint( date, score.intValue(), nBackVersion );
    }
}
