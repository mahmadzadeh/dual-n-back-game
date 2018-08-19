package com.dualnback;

import java.util.TimerTask;

public class NewUpdateTimerTask extends TimerTask {

    private final MyMainThreadHandler handler;

    public NewUpdateTimerTask( MyMainThreadHandler handler ) {
        this.handler = handler;
    }

    @Override
    public void run( ) {
        handler.obtainMessage( 1 ).sendToTarget();
    }
}
