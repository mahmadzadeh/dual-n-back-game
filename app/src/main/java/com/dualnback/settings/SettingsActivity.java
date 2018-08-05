package com.dualnback.settings;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.dualnback.R;

import java.util.List;

public class SettingsActivity extends AppCompatPreferenceActivity {

    protected static OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new SettingsPreferenceChangeListener();

    private static boolean isXLargeTablet( Context context ) {
        return ( context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK ) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar( ) {
        ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null ) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled( true );
        }
    }

    @Override
    public boolean onMenuItemSelected( int featureId, MenuItem item ) {
        int id = item.getItemId();
        if ( id == android.R.id.home ) {
            if ( !super.onMenuItemSelected( featureId, item ) ) {
                NavUtils.navigateUpFromSameTask( this );
            }
            return true;
        }
        return super.onMenuItemSelected( featureId, item );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane( ) {
        return isXLargeTablet( this );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders( List<Header> target ) {
        loadHeadersFromResource( R.xml.pref_headers, target );
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment( String fragmentName ) {
        return PreferenceFragment.class.getName().equals( fragmentName )
                || GeneralPreferenceFragment.class.getName().equals( fragmentName )
                || ScorePreferenceFragment.class.getName().equals( fragmentName );
    }

}
