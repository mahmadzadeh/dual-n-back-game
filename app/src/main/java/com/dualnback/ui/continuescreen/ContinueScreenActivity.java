package com.dualnback.ui.continuescreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dualnback.R;
import com.dualnback.game.NBackVersion;
import com.dualnback.ui.chartscreen.ChartActivity;
import com.dualnback.util.DateUtil;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.dualnback.ui.mainscreen.MainActivity.FINAL_SCORE;
import static com.dualnback.ui.mainscreen.MainActivity.VERSION;
import static com.dualnback.ui.startscreen.util.StartScreenActivityIntentUtil.backToStartScreen;
import static com.dualnback.util.IntentUtility.extractFromExtrasWithDefault;
import static com.dualnback.util.IntentUtility.extractGameVersion;
import static com.dualnback.util.NumberFormatterUtil.formatScore;


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
