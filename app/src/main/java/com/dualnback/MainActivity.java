package com.dualnback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dualnback.game.NBackVersion;
import com.dualnback.game.factory.GameParameters;
import com.dualnback.game.factory.SoundCollectionFactory;
import com.dualnback.location.LocationCollection;
import com.dualnback.presenter.MainActivityPresenter;
import com.dualnback.settings.ApplicationConfig;
import com.dualnback.settings.ConfigReader;
import com.dualnback.sound.SoundCollection;
import com.dualnback.util.IntentUtility;
import com.dualnback.view.MainScreenView;

import java.util.Timer;
import java.util.TimerTask;

import static com.dualnback.R.drawable.checkmark;
import static com.dualnback.R.drawable.xmark;
import static com.dualnback.StartScreenActivity.DEFAULT_VERSION;
import static com.dualnback.StartScreenActivity.N_BACK_VERSION;
import static com.dualnback.presenter.MainActivityPresenter.TIME_TEXT_NORMAL_COLOUR;

public class MainActivity extends AppCompatActivity implements MainScreenView {

    public static final String FINAL_SCORE = "FINAL_SCORE";
    public static final String VERSION = "VERSION";
    public static final int EXPECTED_SOUND_MATCHES = 7;
    public static final int EXPECTED_LOC_MATCHES = 7;
    public static final int TOTAL_TRIAL_COUNT = 25;
    public static final int COUNT_DOWN_INTERVAL_IN_MILLIS = 1000;
    private final Timer gameUpdateTimer = new Timer( false );
    private TextView countDownTextView;
    private Button soundMatchButton;
    private Button locationMatchButton;
    private TextView scoreTxt;
    private ImageView positionMatchFeedBackImg;
    private ImageView soundMatchFeedBackImg;
    private TextView gameVersionText;
    private NBackVersion version;
    private Vibrator vibrator;

    private MainActivityPresenter presenter = null;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        version = IntentUtility.<NBackVersion>extractFromIntentExtras( this.getIntent().getExtras(), N_BACK_VERSION ).orElse( DEFAULT_VERSION );

        if ( presenter == null ) {
            presenter = new MainActivityPresenter( this,
                    new GameParameters()
                            .withVersion( version )
                            .withContext( this )
                            .withExpectedSoundMatches( EXPECTED_SOUND_MATCHES )
                            .withExpectedLocationMatches( EXPECTED_LOC_MATCHES )
                            .withLocationCollection( new LocationCollection() )
                            .withSoundCollection( new SoundCollection( SoundCollectionFactory.instance( this ) ) )
                            .withConfig( new ApplicationConfig( new ConfigReader( this ) ) ) );
        }

        setContentView( R.layout.activity_main );

        initGameUiElements( version );

        countDownTextView = findViewById( R.id.textViewCountDownTimer );

        vibrator = ( Vibrator ) getSystemService( Context.VIBRATOR_SERVICE );

        locationMatchButton.setOnClickListener( ( View view ) -> {
            presenter.handleLocationButtonClick();
        } );

        soundMatchButton.setOnClickListener( ( View view ) -> {
            presenter.handleSoundButtonClick();
        } );

        presenter.startTimer();

        presenter.startTrial();
    }

    public void updateLocationFeedBackImage( ) {
        gameUpdateTimer.schedule( new TimerTask() {
            @Override
            public void run( ) {
                runOnUiThread( ( ) ->
                        positionMatchFeedBackImg.setImageResource( R.drawable.transparent ) );
            }
        }, 500 );
    }

    @Override
    public void updateSoundFeedBackImage( ) {
        gameUpdateTimer.schedule( new TimerTask() {
            @Override
            public void run( ) {
                runOnUiThread( ( ) ->
                        soundMatchFeedBackImg.setImageResource( R.drawable.transparent ) );
            }
        }, 500 );
    }

    @Override
    public void setPositionMatchFeedBack( boolean isCorrectAnswer ) {
        positionMatchFeedBackImg.setImageResource( isCorrectAnswer ? checkmark : xmark );
    }

    @Override
    public void setSoundMatchFeedBack( boolean isCorrectAnswer ) {
        soundMatchFeedBackImg.setImageResource( isCorrectAnswer ? checkmark : xmark );
    }

    @Override
    public void vibrateFor( int vibrationLength ) {
        vibrator.vibrate( vibrationLength );
    }

    @Override
    public void setCountDownText( String text ) {
        setCountDownTextAndColor( text, TIME_TEXT_NORMAL_COLOUR );
    }

    @Override
    public void setCountDownTextAndColor( String text, int color ) {
        countDownTextView.setTextColor( color );
        countDownTextView.setText( text );
    }

    @Override
    public void setScoreTextRound( String text ) {
        scoreTxt.setText( text );
    }

    public void onFinish( double currentScore ) {

        setCountDownText( "00:00" );

        Intent countDownIntent = new Intent( this, ContinueScreenActivity.class );

        countDownIntent.putExtra( FINAL_SCORE, currentScore );
        countDownIntent.putExtra( VERSION, version.getTextRepresentation() );

        startActivity( countDownIntent );
    }

    @Override
    public void updateCellState( int cell, int state ) {
        ImageView imageView = findViewById( cell );
        imageView.setImageResource( state );
    }

    private void initGameUiElements( NBackVersion version ) {
        soundMatchButton = findViewById( R.id.soundMatchButton );

        locationMatchButton = findViewById( R.id.positionMatchButton );

        scoreTxt = findViewById( R.id.textViewScore );

        positionMatchFeedBackImg = findViewById( R.id.positionMatchFeedBackImg );

        soundMatchFeedBackImg = findViewById( R.id.soundMatchFeedBackImg );

        gameVersionText = findViewById( R.id.textViewGameName );

        gameVersionText.setText( version.getTextRepresentation() );
    }
}
