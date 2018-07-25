package com.dualnback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dualnback.game.Cell;
import com.dualnback.game.DualBackGame;
import com.dualnback.game.NBackVersion;
import com.dualnback.game.Trial;
import com.dualnback.game.factory.DualBackGameFactory;
import com.dualnback.game.factory.GameParameters;
import com.dualnback.game.factory.NullTrial;
import com.dualnback.game.factory.SoundCollectionFactory;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import static com.dualnback.StartScreenActivity.DEFAULT_VERSION;
import static com.dualnback.StartScreenActivity.N_BACK_VERSION;
import static com.dualnback.game.LocationToImageMapper.map;
import static com.dualnback.util.IntentUtility.extractFromIntentExtras;
import static com.dualnback.util.NumberFormatterUtil.formatScore;

public class MainActivity extends AppCompatActivity implements SwappableImage {

    public static final String FINAL_SCORE = "FINAL_SCORE";
    public static final String VERSION = "VERSION";
    public static final int EXPECTED_SOUND_MATCHES = 7;
    public static final int EXPECTED_LOC_MATCHES = 7;
    public static final int VIBERATION_MILLISECONDS = 100;
    public final int ONE_ROUND_IN_MILLIS = 75000;
    public final int COUNT_DOWN_INTERVAL_IN_MILLIS = 1000;

    private DualBackGame dualBackGame;
    private Button soundMatchButton;
    private Button locationMatchButton;
    private TextView scoreTxt;
    private ImageView positionMatchFeedBackImg;
    private ImageView soundMatchFeedBackImg;

    private CountDownText countdownTimerTxt;
    private GameCountDownTimer timer;
    private Handler handler;
    private TextView gameVersionText;
    private NBackVersion version;

    private Trial currentTrial = new NullTrial();

    private Vibrator vibrator;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );

        Optional<NBackVersion> mayBeVersion = extractFromIntentExtras( this.getIntent().getExtras(), N_BACK_VERSION );
        version = mayBeVersion.orElse( DEFAULT_VERSION );

        GameParameters parameters = new GameParameters()
                .withVersion( version )
                .withContext( this )
                .withExpectedSoundMatches( EXPECTED_SOUND_MATCHES )
                .withExpectedLocationMatches( EXPECTED_LOC_MATCHES )
                .withLocationCollection( new LocationCollection() )
                .withSoundCollection( new SoundCollection( SoundCollectionFactory.instance( this ) ) );

        dualBackGame = DualBackGameFactory.create( parameters );

        initGameUiElements( version );

        countdownTimerTxt = new CountDownText( findViewById( R.id.textViewCountDownTimer ) );

        vibrator = ( Vibrator ) getSystemService( Context.VIBRATOR_SERVICE );

        handler = new Handler() {
            public void handleMessage( Message m ) {
                currentTrial = dualBackGame.markStartOfTrial();
                currentTrial.getSound().playSound( MainActivity.this );
            }
        };

        locationMatchButton.setOnClickListener( view -> {
            boolean isCorrectAnswer = dualBackGame.recordLocationMatch( currentTrial );
            vibrator.vibrate( VIBERATION_MILLISECONDS );
            positionMatchFeedBackImg.setImageResource( isCorrectAnswer ?
                    R.drawable.checkmark :
                    R.drawable.xmark
            );

            new Timer( false ).schedule( new TimerTask() {
                @Override
                public void run( ) {
                    runOnUiThread( ( ) -> positionMatchFeedBackImg.setImageResource( R.drawable.transparent ) );
                }
            }, 500 );
        } );

        soundMatchButton.setOnClickListener( ( View view ) -> {
            boolean isCorrectAnswer = dualBackGame.recordSoundMatch( currentTrial );
            vibrator.vibrate( VIBERATION_MILLISECONDS );
            soundMatchFeedBackImg.setImageResource( isCorrectAnswer ?
                    R.drawable.checkmark :
                    R.drawable.xmark );

            new Timer( false ).schedule( new TimerTask() {
                @Override
                public void run( ) {
                    runOnUiThread( ( ) -> soundMatchFeedBackImg.setImageResource( R.drawable.transparent ) );
                }
            }, 500 );
        } );

        timer = GameCountDownTimer.INSTANCE( this, ONE_ROUND_IN_MILLIS, COUNT_DOWN_INTERVAL_IN_MILLIS );
        timer.start();
    }

    public void setCountDownText( String text ) {
        countdownTimerTxt.setText( text );
    }

    public void markEndOfOneRound( ) {
        dualBackGame.markEndOfTrial( currentTrial );
        scoreTxt.setText( formatScore( dualBackGame.getCurrentScore() ) );
        updateUI();
    }

    public void swapImage( Cell cell, int resourceId ) {
        dualBackGame
                .findCellLocation( cell )
                .ifPresent( cellLocation -> {
                    ImageView imageView = findViewById( map( cellLocation ) );
                    imageView.setImageResource( resourceId );
                } );
    }

    public double currentPoints( ) {
        return dualBackGame.getCurrentScore();
    }

    public void onFinish( ) {

        setCountDownText( "00:00" );

        Intent countDownIntent = new Intent( this, ContinueScreenActivity.class );

        double currentPoints = currentPoints();

        countDownIntent.putExtra( FINAL_SCORE, currentPoints );
        countDownIntent.putExtra( VERSION, version.getTextRepresentation() );

        startActivity( countDownIntent );
    }

    private void updateUI( ) {
        long TIMER_DELAY = 1;
        final Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            public void run( ) {
                handler.obtainMessage( 1 ).sendToTarget();
            }
        }, TIMER_DELAY );
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
