package com.dualnback;

import java.util.TimerTask;

public class NewUpdateTimerTask extends TimerTask {

    private final GameMainThreadHandler handler;

    public NewUpdateTimerTask( GameMainThreadHandler handler ) {
        this.handler = handler;
    }

    @Override
    public void run( ) {
        handler.obtainMessage( 1 ).sendToTarget();
    }
}
