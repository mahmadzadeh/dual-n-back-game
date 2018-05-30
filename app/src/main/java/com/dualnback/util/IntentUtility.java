package com.dualnback.util;

import android.os.Bundle;

import com.dualnback.ContinueScreenActivity;
import com.dualnback.dao.DataPoint;

import java.util.Date;
import java.util.Optional;

import static com.dualnback.MainActivity.FINAL_SCORE;

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

    public static DataPoint extractDatePointFromExtras( Bundle extras ) {
        int score = 0;
        Date date = new Date();

        if ( extras != null ) {
            score = Integer.valueOf( extras.getString( FINAL_SCORE ) );
            date = DateUtil.parse( extras.getString( ContinueScreenActivity.DATE ) );
        }
        return new DataPoint( date, score );
    }
}
