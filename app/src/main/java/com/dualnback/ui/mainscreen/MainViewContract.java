package com.dualnback.ui.mainscreen;

public interface MainViewContract {

    interface Presenter {

        void handleLocationButtonClick( );

        void handleSoundButtonClick( );

        void setCountDownText( String text );

        void endTrial( );

        void startTrial( );

        void onFinish( );

        void startTimer( );
    }
}
