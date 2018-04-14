package com.dualnback;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dualnback.game.Cell;
import com.dualnback.game.DualBackGame;
import com.dualnback.game.GameTrialCollection;
import com.dualnback.game.LocationToImageMapper;
import com.dualnback.game.NBackVersion;
import com.dualnback.game.Trial;
import com.dualnback.game.factory.GridFactory;
import com.dualnback.game.factory.SoundCollectionFactory;
import com.dualnback.game.factory.TrialListFactory;
import com.dualnback.location.Location;
import com.dualnback.location.LocationCollection;
import com.dualnback.random.RandomTrialGenerator;
import com.dualnback.sound.SoundCollection;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SwappableImage {

    public static final String FINAL_SCORE = "FINAL_SCORE";
    public final int ONE_ROUND_IN_MILLIS = 90000;
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

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        soundCollection = new SoundCollection( SoundCollectionFactory.instance( this ) );
        List<Trial> trials = TrialListFactory.create( new RandomTrialGenerator( locationCollection, soundCollection ) );

        GameTrialCollection gameTrialCollection = new GameTrialCollection( version, trials );

        Log.i( "MainActivity", "Trials: " + gameTrialCollection.toString() );

        dualBackGame = new DualBackGame( GridFactory.instance( this ), gameTrialCollection );

        soundMatchButton = findViewById( R.id.soundMatchButton );
        locationMatchButton = findViewById( R.id.positionMatchButton );
        scoreTxt = findViewById( R.id.textViewScore );
        countdownTimerTxt = findViewById( R.id.textViewCountDownTImer );
        gridLayout = findViewById( R.id.gridLayout );

        handler = new Handler() {

            public void handleMessage( Message m ) {
                currentTrial = dualBackGame.markStartOfTrial();
            }
        };

        locationMatchButton.setOnClickListener( view -> dualBackGame.recordLocationMatch() );

        soundMatchButton.setOnClickListener( view -> dualBackGame.recordSoundMatch() );

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
        Optional<Location> cellLocation = dualBackGame.findCellLocation( cell );
        if ( cellLocation.isPresent() ) {
            ImageView imageView = findViewById( LocationToImageMapper.map( cellLocation.get() ) );
            imageView.invalidate();
            imageView.setImageResource( resourceId );
        }
    }

    public int currentPoints( ) {
        return 0;
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
