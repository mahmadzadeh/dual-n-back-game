package com.dualnback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dualnback.game.NBackVersion;

public class StartScreenActivity extends AppCompatActivity {
    public static final String N_BACK_VERSION = "nBackVersion";
    Spinner spinner;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_start_screen );

        spinner = findViewById( R.id.nBackVersion );
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this,
                R.array.nBackArray, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner.setAdapter( adapter );

        final Button button = findViewById( R.id.continueButton );

        button.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick( View v ) {
                        Intent mainActivityIntent = new Intent( v.getContext(), MainActivity.class );
                        mainActivityIntent.putExtra( N_BACK_VERSION, NBackVersion.TwoBack );
                        startActivity( mainActivityIntent );
                    }
                } );

    }

}
