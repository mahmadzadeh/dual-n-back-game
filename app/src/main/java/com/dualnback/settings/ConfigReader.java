package com.dualnback.settings;


import android.content.Context;
import android.content.SharedPreferences;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.dualnback.util.NumberFormatterUtil.convertToIntOrDefault;

public class ConfigReader {

    private final Context context;

    public ConfigReader( Context context ) {
        this.context = context;
    }

    public int getStringPreferenceWithCastToInt( String key, String defaultVal ) {
        String value = defaultVal;

        SharedPreferences preferences = getDefaultSharedPreferences( context );

        if ( preferences != null ) {
            value = preferences.getString( key, defaultVal );
        }

        return convertToIntOrDefault( value, Integer.valueOf( defaultVal ) );
    }

    public String getStringPreference( String key, String defaultVal ) {

        String value = null;

        SharedPreferences preferences = getDefaultSharedPreferences( context );

        if ( preferences != null ) {
            value = preferences.getString( key, defaultVal );
        }

        return value;
    }

    public int getIntPreference( String key, int defaultVal ) {

        int value = defaultVal;

        SharedPreferences preferences = getDefaultSharedPreferences( context );

        if ( preferences != null ) {
            value = preferences.getInt( key, defaultVal );
        }

        return value;
    }
}
