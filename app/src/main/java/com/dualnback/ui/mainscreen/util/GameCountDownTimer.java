package com.dualnback.ui.mainscreen.util;

import android.os.CountDownTimer;

import com.dualnback.ui.mainscreen.MainViewContract;

import static com.dualnback.ui.mainscreen.util.TimerUtil.formatTime;
import static com.dualnback.ui.mainscreen.util.TimerUtil.isEndOfTrialYet;

public class GameCountDownTimer implements DownTimerContract {

    private final MainViewContract.Presenter presenter;
    private final long oneTickDurationMillis;
    private final long oneTrialInMillis;
    private long singleGameLengthMillis;
    private CountDownTimer timer;

    private GameCountDownTimer( MainViewContract.Presenter presenter, long singleGameLengthMillis,
                                long oneTickDurationMillis, long oneTrialInMillis ) {


        this.presenter = presenter;
        this.singleGameLengthMillis = singleGameLengthMillis;
        this.oneTickDurationMillis = oneTickDurationMillis;
        this.oneTrialInMillis = oneTrialInMillis;
    }

    public static GameCountDownTimer INSTANCE( MainViewContract.Presenter presenter,
                                               long singleGameLengthMillis,
                                               long oneTickDurationMillis,
                                               long oneTrialInMillis ) {

        return new GameCountDownTimer( presenter, singleGameLengthMillis, oneTickDurationMillis, oneTrialInMillis );
    }

    @Override
    public void onTick( long millisUntilFinished ) {
        this.singleGameLengthMillis = millisUntilFinished;

        presenter.setCountDownText( formatTime( millisUntilFinished ) );

        if ( isEndOfTrialYet( millisUntilFinished, this.oneTrialInMillis ) ) {
            presenter.endTrial();
        }
    }

    @Override
    public void onFinish( ) {
        presenter.onFinish();
    }

    @Override
    public void start( ) {
        this.timer = createTimer();
        this.timer.start();
    }

    @Override
    public void onPause( ) {
        if ( timer != null ) {
            timer.cancel();
        }
    }

    private CountDownTimer createTimer( ) {

        CountDownTimer countDownTimer = new CountDownTimer( this.singleGameLengthMillis, this.oneTickDurationMillis ) {
            @Override
            public void onTick( long millisUntilFinished ) {
                GameCountDownTimer.this.onTick( millisUntilFinished );
            }

            @Override
            public void onFinish( ) {
                GameCountDownTimer.this.onFinish();
            }
        };

        return countDownTimer;
    }
}
