package com.dualnback.util;

import android.os.Bundle;

import com.dualnback.ContinueScreenActivity;
import com.dualnback.dao.DataPoint;
import com.dualnback.game.NBackVersion;

import java.util.Date;
import java.util.Optional;

import static com.dualnback.MainActivity.FINAL_SCORE;
import static com.dualnback.MainActivity.VERSION;

public class IntentUtility {

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

    public static Optional<NBackVersion> extractGameVersion( Bundle extras ) {

        Optional<NBackVersion> version = Optional.empty();

        if ( extras != null ) {
            String string = extras.getString( VERSION );

            version = NBackVersion.fromTextValue( string );
        }

        return version;
    }

    public static DataPoint extractDatePointFromExtras( Bundle extras ) {
        Float score = 0.00f;
        Date date = new Date();

        if ( extras != null ) {
            String string = extras.getString( FINAL_SCORE );

            score = Float.valueOf( string );

            date = DateUtil.parse( extras.getString( ContinueScreenActivity.DATE ) );
        }
        return new DataPoint( date, score.intValue(), null );
    }
}
