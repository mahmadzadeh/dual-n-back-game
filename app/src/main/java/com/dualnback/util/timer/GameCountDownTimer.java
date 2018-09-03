package com.dualnback.util.timer;

import android.os.CountDownTimer;

import com.dualnback.MainActivity;

import static com.dualnback.util.timer.TimerUtil.formatTime;
import static com.dualnback.util.timer.TimerUtil.isEndOfTrialYet;

public class GameCountDownTimer extends CountDownTimer {

    private static GameCountDownTimer instance;
    private MainActivity gameScreenActivity;
    private long oneTrialInMillis;

    /**
     * @param singleGameLengthMillis The number of millis in the future from the call
     *                               to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                               is called.
     * @param oneTickDurationMillis  The interval along the way to receive
     *                               {@link #onTick(long)} callbacks.
     * @param oneTrialInMillis
     */
    private GameCountDownTimer( MainActivity gameScreenActivity, long singleGameLengthMillis, long oneTickDurationMillis, long oneTrialInMillis ) {
        super( singleGameLengthMillis, oneTickDurationMillis );

        this.gameScreenActivity = gameScreenActivity;
        this.oneTrialInMillis = oneTrialInMillis;
    }

    public static GameCountDownTimer INSTANCE( MainActivity gameScreenActivity,
                                               long singleGameLengthMillis,
                                               long oneTickDurationMillis,
                                               long oneTrialInMillis ) {

        return new GameCountDownTimer( gameScreenActivity, singleGameLengthMillis, oneTickDurationMillis, oneTrialInMillis );
    }

    @Override
    public void onTick( long millisUntilFinished ) {

        gameScreenActivity.setCountDownText( formatTime( millisUntilFinished ) );

        if ( isEndOfTrialYet( millisUntilFinished, this.oneTrialInMillis ) ) {
            gameScreenActivity.markEndOfOneRound();
        }
    }

    @Override
    public void onFinish( ) {
        gameScreenActivity.onFinish();
    }
}
