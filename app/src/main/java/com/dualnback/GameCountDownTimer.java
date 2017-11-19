package com.dualnback;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class GameCountDownTimer extends CountDownTimer {

    private MainActivity gameScreenActivity;
    private int oneRoundInSeconds;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public GameCountDownTimer( MainActivity gameScreenActivity, long millisInFuture, long countDownInterval ) {
        super( millisInFuture, countDownInterval );
        this.gameScreenActivity = gameScreenActivity;
        this.oneRoundInSeconds  = 4;
    }

    @Override
    public void onTick( long millisUntilFinished ) {
        String text = String.format( "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished ) - TimeUnit.HOURS.toMinutes( TimeUnit.MILLISECONDS.toHours( millisUntilFinished ) ),
                TimeUnit.MILLISECONDS.toSeconds( millisUntilFinished ) - TimeUnit.MINUTES.toSeconds( TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished ) )
        );

        gameScreenActivity.setCountDownText( text );

        oneRoundInSeconds--;

        if ( oneRoundInSeconds == 0) {
            gameScreenActivity.markEndOfOneRound();
            oneRoundInSeconds = 4;
        }
    }

    @Override
    public void onFinish( ) {
        gameScreenActivity.setCountDownText( "00:00" );

        Intent countDownIntent = new Intent( gameScreenActivity, ContinueScreenActivity.class );

        countDownIntent.putExtra( gameScreenActivity.FINAL_SCORE, gameScreenActivity.currentPoints() );

        gameScreenActivity.startActivity( countDownIntent );
    }
}
