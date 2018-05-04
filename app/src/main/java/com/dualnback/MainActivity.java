package com.dualnback;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dualnback.game.Cell;
import com.dualnback.game.DualBackGame;
import com.dualnback.game.NBackVersion;
import com.dualnback.game.Trial;
import com.dualnback.game.factory.DualBackGameFactory;
import com.dualnback.game.factory.GameParameters;
import com.dualnback.game.factory.SoundCollectionFactory;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;

import java.util.Timer;
import java.util.TimerTask;

import static com.dualnback.StartScreenActivity.N_BACK_VERSION;
import static com.dualnback.game.LocationToImageMapper.map;
import static com.dualnback.game.NBackVersion.TwoBack;
import static com.dualnback.util.IntentUtility.extractFromExtrasWithDefault;
import static com.dualnback.util.NumberFormatterUtil.formatScore;

public class MainActivity extends AppCompatActivity implements SwappableImage {

    public static final String FINAL_SCORE = "FINAL_SCORE";
    public static final int EXPECTED_SOUND_MATCHES = 7;
    public static final int EXPECTED_LOC_MATCHES = 7;
    public static final int VIBERATION_MILLISECONDS = 100;
    public final int ONE_ROUND_IN_MILLIS = 72000;
    public final int COUNT_DOWN_INTERVAL_IN_MILLIS = 1000;

    private DualBackGame dualBackGame;
    private Button soundMatchButton;
    private Button locationMatchButton;
    private TextView scoreTxt;
    private TextView countdownTimerTxt;
    private GameCountDownTimer timer;
    private Handler handler;

    private Trial currentTrial;

    private GridLayout gridLayout;
    private Vibrator v;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );

        NBackVersion version = extractFromExtrasWithDefault( this.getIntent().getExtras(), N_BACK_VERSION, TwoBack );

        GameParameters parameters = new GameParameters(
                version,
                this,
                EXPECTED_SOUND_MATCHES,
                EXPECTED_LOC_MATCHES,
                new LocationCollection(),
                new SoundCollection( SoundCollectionFactory.instance( this ) ) );

        dualBackGame = DualBackGameFactory.create( parameters );

        soundMatchButton = findViewById( R.id.soundMatchButton );
        locationMatchButton = findViewById( R.id.positionMatchButton );
        scoreTxt = findViewById( R.id.textViewScore );
        countdownTimerTxt = findViewById( R.id.textViewCountDownTImer );
        gridLayout = findViewById( R.id.gridLayout );
        v = ( Vibrator ) getSystemService( Context.VIBRATOR_SERVICE );

        handler = new Handler() {

            public void handleMessage( Message m ) {
                currentTrial = dualBackGame.markStartOfTrial();
                currentTrial.getSound().playSound( MainActivity.this );
            }
        };

        locationMatchButton.setOnClickListener( view -> {
            dualBackGame.recordLocationMatch();
            v.vibrate( VIBERATION_MILLISECONDS );
        } );

        soundMatchButton.setOnClickListener( view -> {
            dualBackGame.recordSoundMatch();
            v.vibrate( VIBERATION_MILLISECONDS );
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

    private void updateUI( ) {
        long TIMER_DELAY = 1;
        final Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            public void run( ) {
                handler.obtainMessage( 1 ).sendToTarget();
            }
        }, TIMER_DELAY );
    }
}
