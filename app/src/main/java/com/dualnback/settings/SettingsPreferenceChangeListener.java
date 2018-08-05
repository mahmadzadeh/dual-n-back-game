package com.dualnback.settings;

import android.preference.Preference;

class SettingsPreferenceChangeListener implements Preference.OnPreferenceChangeListener {

    @Override
    public boolean onPreferenceChange( Preference preference, Object value ) {

        String stringValue = value.toString();
        preference.setSummary( stringValue );

        return true;
    }
}
