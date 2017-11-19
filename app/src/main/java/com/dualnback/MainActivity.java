package com.dualnback;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dualnback.game.DualBackGame;
import com.dualnback.game.GridFactory;
import com.dualnback.game.NBackVersion;
import com.dualnback.game.SoundCollectionFactory;
import com.dualnback.game.SoundLocation;
import com.dualnback.location.LocationCollection;
import com.dualnback.sound.SoundCollection;

import java.util.Timer;
import java.util.TimerTask;

import static com.dualnback.game.LocationToImageMapper.map;

public class MainActivity extends AppCompatActivity {

    public static final String FINAL_SCORE = "FINAL_SCORE";
    public final int ONE_ROUND_IN_MILLIS = 90000;
    public final int COUNT_DOWN_INTERVAL_IN_MILLIS = 1000;

    private final NBackVersion version = NBackVersion.TwoBack;
    private DualBackGame dualBackGame;
    private Button soundMatch;
    private Button positionMatchButton;
    private TextView scoreTxt;
    private TextView countdownTimerTxt;
    private GameCountDownTimer timer;
    private Handler handler;
    private SoundCollection soundCollection = new SoundCollection( SoundCollectionFactory.instance() );
    private LocationCollection locationCollection = new LocationCollection();

    private GridLayout gridLayout;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        dualBackGame = new DualBackGame( GridFactory.instance(), version );

        soundMatch = findViewById( R.id.soundMatchButton );
        positionMatchButton = findViewById( R.id.positionMatchButton );
        scoreTxt = findViewById( R.id.textViewScore );
        countdownTimerTxt = findViewById( R.id.textViewCountDownTImer );
        gridLayout = findViewById( R.id.grid_layout );

        handler = new Handler() {
            public void handleMessage( Message m ) {

                turnOffImageBasedOnCurrentLocation();

                SoundLocation randomSoundAndLocation = dualBackGame.getRandomSoundAndLocation( soundCollection, locationCollection );
                dualBackGame.updateGame( randomSoundAndLocation );

                updateImageBasedOnGameState();
                randomSoundAndLocation.getSound().playSound( MainActivity.this );
            }
        };


        positionMatchButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick( View view ) {
                        dualBackGame.evaluateLocation();
                    }
                }
        );

        soundMatch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick( View view ) {
                        dualBackGame.evaluateSound();
                    }
                }
        );

        updateUI();

        timer = new GameCountDownTimer( this, ONE_ROUND_IN_MILLIS, COUNT_DOWN_INTERVAL_IN_MILLIS );
        timer.start();
    }

    private void updateImageBasedOnGameState( ) {
        if ( dualBackGame.getCurrentSoundLocation().isPresent() ) {
            ImageView imageView = findViewById(
                    map( dualBackGame.getCurrentSoundLocation().get().getLocation() ) );

            imageView.setImageResource( R.mipmap.square_blue );
        }
    }

    private void turnOffImageBasedOnCurrentLocation( ) {
        if ( dualBackGame.getCurrentSoundLocation().isPresent() ) {
            ImageView imageView = findViewById(
                    map( dualBackGame.getCurrentSoundLocation().get().getLocation() ) );

            imageView.setImageResource( R.mipmap.square );
        }
    }

    public void setCountDownText( String text ) {
        countdownTimerTxt.setText( text );
    }

    public void markEndOfOneRound( ) {
        dualBackGame.markEndOfOneRound();
        updateUI();
    }

    public int currentPoints( ) {
        return 0;
    }

    private void updateUI( ) {
        long TIMER_DELAY = 10;
        final Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            public void run( ) {
                handler.obtainMessage( 1 ).sendToTarget();
            }
        }, TIMER_DELAY );
    }
}
