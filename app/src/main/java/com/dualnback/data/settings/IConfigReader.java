package com.dualnback.data.settings;

interface IConfigReader {

    int getStringPreferenceWithCastToInt( String key, String defaultVal );

    String getStringPreference( String key, String defaultVal );

    int getIntPreference( String key, int defaultVal );
}
