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

import com.dualnback.location.LocationCollection;
import com.dualnback.sound.ASound;
import com.dualnback.sound.BSound;
import com.dualnback.sound.CSound;
import com.dualnback.sound.ESound;
import com.dualnback.sound.Sound;
import com.dualnback.sound.SoundCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private SoundCollection soundCollection;
    private LocationCollection locationCollection;
    private GridLayout gridLayout;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        List<Sound> soundClips = new ArrayList<>();

        soundClips.add( new ASound() );
        soundClips.add( new BSound() );
        soundClips.add( new CSound() );
        soundClips.add( new ESound() );

        locationCollection = new LocationCollection();

        dualBackGame = new DualBackGame( new DualBackGrid(), version );

        soundMatch = findViewById( R.id.soundMatchButton );
        positionMatchButton = findViewById( R.id.positionMatchButton );
        scoreTxt = findViewById( R.id.textViewScore );
        countdownTimerTxt = findViewById( R.id.textViewCountDownTImer );
        gridLayout = findViewById( R.id.grid_layout );

        timer = new GameCountDownTimer( this, ONE_ROUND_IN_MILLIS, COUNT_DOWN_INTERVAL_IN_MILLIS );

        SoundLocation randomSoundAndLocation = dualBackGame.getRandomSoundAndLocation( new SoundCollection( soundClips ), locationCollection );
        ImageView imageView = findViewById( R.id.image_00 );

        imageView.setImageResource( R.mipmap.square_blue );

        timer.start();

        updateUI();

        handler = new Handler() {
            public void handleMessage( Message m ) {
                Log.d( "HANDLE MESSAGE", "handleMessage: " );
            }
        };
    }

    public void setCountDownText( String text ) {
        countdownTimerTxt.setText( text );
    }

    public int currentPoints( ) {
        return 0;
    }

    private void updateUI( ) {
        long TIMER_DELAY = 100;
        final Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            public void run( ) {
                handler.obtainMessage( 1 ).sendToTarget();
            }
        }, TIMER_DELAY );
    }
}
