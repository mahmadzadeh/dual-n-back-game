package com.dualnback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.dualnback.util.DateUtil;

import java.util.Date;

import static com.dualnback.MainActivity.FINAL_SCORE;
import static com.dualnback.util.IntentUtility.extractFromExtrasWithDefault;
import static com.dualnback.util.NumberFormatterUtil.formatScore;
import static com.dualnback.util.StartScreenActivityIntentUtil.backToStartScreen;


public class ContinueScreenActivity extends AppCompatActivity {

    public static final String DATE = "GAME_DATE";
    public static final String SCORE_TEXT = "Score ";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.continue_screen );

        final Button saveButton = ( Button ) findViewById( R.id.saveButton );
        final Button continueButton = ( Button ) findViewById( R.id.continueButton );
        final Button quitButton = ( Button ) findViewById( R.id.quitButton );

        final TextView scoreTextView = ( TextView ) findViewById( R.id.score );

        Double score = extractFromExtrasWithDefault( getIntent().getExtras(), FINAL_SCORE, 0.0D );

        final String actualScore = formatScore( score );

        scoreTextView.setText( SCORE_TEXT + actualScore );

        continueButton.setOnClickListener(
                view ->
                        backToStartScreen( view, ContinueScreenActivity.this )
        );

        quitButton.setOnClickListener(
                view ->
                        backToStartScreen( view, ContinueScreenActivity.this )
        );

        saveButton.setOnClickListener( view -> {
            Intent chartActivityIntent = new Intent( view.getContext(), ChartActivity.class );
            chartActivityIntent.putExtra( FINAL_SCORE, score.toString() );
            chartActivityIntent.putExtra( ContinueScreenActivity.DATE, DateUtil.format( new Date() ) );
            startActivity( chartActivityIntent );
        } );
    }

    private String extractScoreFromIntentExtras( ) {
        Bundle extras = getIntent().getExtras();

        double value = 0.0d;

        if ( extras != null ) {
            value = extras.getDouble( FINAL_SCORE );
        }

        return formatScore( value );
    }
}
