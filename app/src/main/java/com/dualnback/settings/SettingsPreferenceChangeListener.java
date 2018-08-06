package com.dualnback.settings;

import android.preference.ListPreference;
import android.preference.Preference;
import android.util.Log;

class SettingsPreferenceChangeListener implements Preference.OnPreferenceChangeListener {

    @Override
    public boolean onPreferenceChange( Preference preference, Object value ) {

        if ( preference instanceof ListPreference ) {
            int index = Integer.parseInt( value.toString() );

            Log.e( "onPreferenceChange", " index is " + index );

            ListPreference lp = ( ListPreference ) preference;

            lp.setValueIndex( index );

        } else {
            String stringValue = value.toString();
            preference.setSummary( stringValue );
        }

        return true;
    }
}
