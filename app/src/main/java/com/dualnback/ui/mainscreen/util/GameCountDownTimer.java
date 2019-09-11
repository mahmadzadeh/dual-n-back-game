package com.dualnback.ui.mainscreen.util;

import android.os.CountDownTimer;

import com.dualnback.ui.mainscreen.MainViewContract;

import static com.dualnback.ui.mainscreen.util.TimerUtil.formatTime;
import static com.dualnback.ui.mainscreen.util.TimerUtil.isEndOfTrialYet;

public class GameCountDownTimer extends CountDownTimer {

    private MainViewContract.Presenter presenter;
    private long oneTrialInMillis;

    private GameCountDownTimer( MainViewContract.Presenter presenter, long singleGameLengthMillis,
                                long oneTickDurationMillis, long oneTrialInMillis ) {

        super( singleGameLengthMillis, oneTickDurationMillis );

        this.presenter = presenter;
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

        presenter.setCountDownText( formatTime( millisUntilFinished ) );

        if ( isEndOfTrialYet( millisUntilFinished, this.oneTrialInMillis ) ) {
            presenter.endTrial();
        }
    }

    @Override
    public void onFinish( ) {
        presenter.onFinish();
    }
}
