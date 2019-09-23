package com.dualnback.ui.startscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.dualnback.R;
import com.dualnback.data.settings.SettingsActivity;
import com.dualnback.game.NBackVersion;
import com.dualnback.ui.mainscreen.MainActivity;
import com.dualnback.ui.startscreen.util.Pulsator;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.ArrayAdapter.createFromResource;
import static com.dualnback.ui.startscreen.util.ArrayAdapterCopyUtil.copyToRegularArrayList;

public class StartScreenActivity extends AppCompatActivity implements StartScreenView {

    public static final int MIN_REQUIRED_SC0RE_TO_GO_TO_NEXT_LVL = 80;
    public static final int MIN_REQUIRED_SC0RE_TO_MAINTAIN_CURRENT_LVL = 60;

    public static final String N_BACK_VERSION = "nBackVersion";

    public static final NBackVersion DEFAULT_VERSION = NBackVersion.TwoBack;

    private Spinner spinner;
    private NBackVersion selectedVersion = DEFAULT_VERSION;
    private ArrayAdapter<CharSequence> adapter;

    private StartScreenPresenter presenter = new StartScreenPresenter( this );

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_start_screen );

        adapter = createFromResource( this, R.array.nBackArray, R.layout.spinner_text );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner = findViewById( R.id.nBackVersion );
        spinner.setAdapter( adapter );


        NBackVersion version = presenter.getCurrentVersion( MIN_REQUIRED_SC0RE_TO_GO_TO_NEXT_LVL,
                MIN_REQUIRED_SC0RE_TO_MAINTAIN_CURRENT_LVL );

        spinner.setSelection( version.map( copyToRegularArrayList( adapter ) ) );

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
                selectedVersion = presenter.getSelectedVersion( item );
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView ) {
                // do nothing
            }
        } );
    }
}
