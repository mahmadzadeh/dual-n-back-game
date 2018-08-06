package com.dualnback.settings;

import android.preference.ListPreference;
import android.preference.Preference;

class SettingsPreferenceChangeListener implements Preference.OnPreferenceChangeListener {

    @Override
    public boolean onPreferenceChange( Preference preference, Object value ) {
        String stringValue = value.toString();
        if ( preference instanceof ListPreference ) {
            ListPreference listPreference = ( ListPreference ) preference;

            int index = listPreference.findIndexOfValue( stringValue );
            preference.setSummary(
                    index >= 0
                            ? listPreference.getEntries()[ index ]
                            : "" );

        } else {
            preference.setSummary( stringValue );
        }

        return true;
    }
}
