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
import com.dualnback.game.GameTrialCollection;
import com.dualnback.game.NBackVersion;
import com.dualnback.game.Trial;
import com.dualnback.game.factory.GridFactory;
import com.dualnback.game.factory.SoundCollectionFactory;
import com.dualnback.location.LocationCollection;
import com.dualnback.random.RandomTrialGenerator;
import com.dualnback.sound.SoundCollection;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.dualnback.game.LocationToImageMapper.map;
import static com.dualnback.game.factory.TrialListFactory.create;
import static com.dualnback.game.factory.TrialListFactory.updateListWithExpectedSoundAndLocationMatch;

public class MainActivity extends AppCompatActivity implements SwappableImage {

    public static final String FINAL_SCORE = "FINAL_SCORE";
    public static final int EXPECTED_SOUND_MATCHES = 7;
    public static final int EXPECTED_LOC_MACTHES = 7;
    public static final int VIBERATION_MILLISECONDS = 100;
    public final int ONE_ROUND_IN_MILLIS = 72000;
    public final int COUNT_DOWN_INTERVAL_IN_MILLIS = 1000;

    private final NBackVersion version = NBackVersion.TwoBack;
    private DualBackGame dualBackGame;
    private Button soundMatchButton;
    private Button locationMatchButton;
    private TextView scoreTxt;
    private TextView countdownTimerTxt;
    private GameCountDownTimer timer;
    private Handler handler;
    private SoundCollection soundCollection;
    private LocationCollection locationCollection = new LocationCollection();
    private Trial currentTrial;

    private GridLayout gridLayout;
    private Vibrator v;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );

        soundCollection = new SoundCollection( SoundCollectionFactory.instance( this ) );

        GameTrialCollection gameTrialCollection = new GameTrialCollection( version,
                getTrials( EXPECTED_SOUND_MATCHES, EXPECTED_LOC_MACTHES ) );

        dualBackGame = new DualBackGame( GridFactory.instance( this ), gameTrialCollection );

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

    private List<Trial> getTrials( int soundMatches, int locMatches ) {
        return updateListWithExpectedSoundAndLocationMatch(
                create( new RandomTrialGenerator( locationCollection, soundCollection ) ),
                soundMatches,
                locMatches,
                version );
    }
}
