package com.dualnback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.dualnback.dao.DataPoint;
import com.dualnback.game.NBackVersion;
import com.dualnback.game.VersionSelection;
import com.dualnback.settings.SettingsActivity;
import com.dualnback.util.Pulsator;

import java.util.Optional;

import static android.widget.ArrayAdapter.createFromResource;
import static com.dualnback.dao.DataFileUtil.readAllData;
import static com.dualnback.util.ArrayAdapterCopyUtil.copyToRegularArray;

public class StartScreenActivity extends AppCompatActivity {
    public static final int MIN_REQUIRED_SC0RE_TO_GO_TO_NEXT_LVL = 80;
    public static final int MIN_REQUIRED_SC0RE_TO_MAINTAIN_CURRENT_LVL = 60;

    public static final String N_BACK_VERSION = "nBackVersion";

    public static final NBackVersion DEFAULT_VERSION = NBackVersion.TwoBack;

    private Spinner spinner;
    private NBackVersion selectedVersion = DEFAULT_VERSION;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_start_screen );

        adapter = createFromResource( this, R.array.nBackArray, R.layout.spinner_text );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner = findViewById( R.id.nBackVersion );
        spinner.setAdapter( adapter );

        Optional<DataPoint> lastDataPoint = readAllData( this.getFilesDir() ).getLastDataPoint();
        NBackVersion version = VersionSelection.currentLevel( lastDataPoint,
                MIN_REQUIRED_SC0RE_TO_GO_TO_NEXT_LVL,
                MIN_REQUIRED_SC0RE_TO_MAINTAIN_CURRENT_LVL );

        spinner.setSelection( version.map( copyToRegularArray( adapter ) ) );

        final ImageButton button = findViewById( R.id.startScreenButton );

        button.setOnClickListener( v -> {
            Intent mainActivityIntent = new Intent( v.getContext(), MainActivity.class );
            mainActivityIntent.putExtra( N_BACK_VERSION, selectedVersion );
            startActivity( mainActivityIntent );

        } );

        final Button settingsButton = findViewById( R.id.settingsButton );
        settingsButton.setTransformationMethod( null );

        settingsButton.setOnClickListener( v -> {
            Intent settingsActivityIntent = new Intent( v.getContext(), SettingsActivity.class );
            startActivity( settingsActivityIntent );
        } );

        Pulsator.pulsate( button );

        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long id ) {
                CharSequence item = adapter.getItem( position );
                selectedVersion = NBackVersion.fromTextValue( item.toString() ).orElse( DEFAULT_VERSION );
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView ) {
                // do nothing
            }
        } );
    }
}
