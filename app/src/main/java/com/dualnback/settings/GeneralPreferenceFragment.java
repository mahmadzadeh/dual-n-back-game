package com.dualnback.settings;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.dualnback.R;

import static com.dualnback.settings.SettingsActivity.sBindPreferenceSummaryToValueListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class GeneralPreferenceFragment extends PreferenceFragment {

    protected static void bindPreferenceSummaryToValue( Preference preference ) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener( sBindPreferenceSummaryToValueListener );

        // Trigger the listener immediately with the preference's
        // current value.
        if ( preference instanceof ListPreference ) {

            sBindPreferenceSummaryToValueListener.onPreferenceChange( preference,
                    PreferenceManager
                            .getDefaultSharedPreferences( preference.getContext() )
                            .getString( preference.getKey(), "" ) );
        } else {
            sBindPreferenceSummaryToValueListener.onPreferenceChange( preference,
                    PreferenceManager
                            .getDefaultSharedPreferences( preference.getContext() )
                            .getInt( preference.getKey(), 3 ) );
        }
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        addPreferencesFromResource( R.xml.pref_general );
        setHasOptionsMenu( true );

        // Bind the summaries of EditText/List/Dialog/Ringtone preferences
        // to their values. When their values change, their summaries are
        // updated to reflect the new value, per the Android Design
        // guidelines.
        bindPreferenceSummaryToValue( findPreference( "vibration_duration_list" ) );
        bindPreferenceSummaryToValue( findPreference( "trial_length" ) );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();
        if ( id == android.R.id.home ) {
            startActivity( new Intent( getActivity(), SettingsActivity.class ) );
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
