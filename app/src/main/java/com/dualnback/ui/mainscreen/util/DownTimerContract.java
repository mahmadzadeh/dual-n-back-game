package com.dualnback.ui.mainscreen.util;

public interface DownTimerContract {

    void onTick( long millisUntilFinished );

    void onFinish( );

    void start( );

    void onPause( );
}
