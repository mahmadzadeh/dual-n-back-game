package com.dualnback;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dualnback.game.NBackVersion;
import com.dualnback.util.DateUtil;
import com.dualnback.view.ChartActivity;

import java.util.Date;

import static com.dualnback.MainActivity.FINAL_SCORE;
import static com.dualnback.MainActivity.VERSION;
import static com.dualnback.util.IntentUtility.extractFromExtrasWithDefault;
import static com.dualnback.util.IntentUtility.extractGameVersion;
import static com.dualnback.util.NumberFormatterUtil.formatScore;
import static com.dualnback.util.StartScreenActivityIntentUtil.backToStartScreen;


public class ContinueScreenActivity extends AppCompatActivity {

    public static final String DATE = "GAME_DATE";
    public static final String SCORE_TEXT = "Score ";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.continue_screen );

        final Button saveButton = findViewById( R.id.saveButton );
        final Button continueButton = findViewById( R.id.continueButton );
        final Button quitButton = findViewById( R.id.quitButton );

        final TextView scoreTextView = findViewById( R.id.score );

        Double score = extractFromExtrasWithDefault( getIntent().getExtras(), FINAL_SCORE, 0.0D );
        NBackVersion version = extractGameVersion( getIntent().getExtras() );

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

        saveButton.setOnClickListener( getOnClickListener( score, version ) );
    }

    @NonNull
    private View.OnClickListener getOnClickListener( Double score, NBackVersion version ) {
        return view -> {
            Intent chartActivityIntent = new Intent( view.getContext(), ChartActivity.class );
            chartActivityIntent.putExtra( FINAL_SCORE, score.toString() );
            chartActivityIntent.putExtra( ContinueScreenActivity.DATE, DateUtil.format( new Date() ) );
            chartActivityIntent.putExtra( VERSION, version.getTextRepresentation() );
            startActivity( chartActivityIntent );
        };
    }
}
