package com.dualnback.settings;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.dualnback.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ScorePreferenceFragment extends PreferenceFragment {

    private static final int DEF_SCORE = 10;

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     */
    protected static void bindPreferenceSummaryToValue( Preference preference ) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener( SettingsActivity.sBindPreferenceSummaryToValueListener );

        // Trigger the listener immediately with the preference's
        // current value.
        SettingsActivity.sBindPreferenceSummaryToValueListener.onPreferenceChange( preference,
                PreferenceManager
                        .getDefaultSharedPreferences( preference.getContext() )
                        .getInt( preference.getKey(), DEF_SCORE ) );
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        addPreferencesFromResource( R.xml.pref_score );
        setHasOptionsMenu( true );

        bindPreferenceSummaryToValue( findPreference( "min_advance_score" ) );
        bindPreferenceSummaryToValue( findPreference( "max_fallback_score" ) );
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
