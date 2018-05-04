package com.dualnback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dualnback.game.NBackVersion;

import static android.widget.ArrayAdapter.createFromResource;

public class StartScreenActivity extends AppCompatActivity {
    public static final String N_BACK_VERSION = "nBackVersion";

    public static final NBackVersion DEFAULT_VERSION = NBackVersion.TwoBack;

    Spinner spinner;
    NBackVersion selectedVersion = DEFAULT_VERSION;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_start_screen );

        spinner = findViewById( R.id.nBackVersion );
        adapter = createFromResource( this,
                R.array.nBackArray, R.layout.spinner_text );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner.setAdapter( adapter );

        final Button button = findViewById( R.id.continueButton );

        button.setOnClickListener( v -> {
            Intent mainActivityIntent = new Intent( v.getContext(), MainActivity.class );
            mainActivityIntent.putExtra( N_BACK_VERSION, selectedVersion );
            startActivity( mainActivityIntent );

        } );

        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long id ) {
                CharSequence item = adapter.getItem( position );
                selectedVersion = NBackVersion.fromUiValue( item.toString() ).orElse( DEFAULT_VERSION );
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView ) {
                // do nothing
            }
        } );
    }

}
