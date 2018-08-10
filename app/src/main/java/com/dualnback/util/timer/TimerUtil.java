package com.dualnback.util.timer;


import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

public class TimerUtil {

    public static boolean isEndOfTrialYet( long millisUntilFinished, long singleTrialInMillis ) {
        return ( millisUntilFinished % singleTrialInMillis ) == 0;
    }

    public static String formatTime( long millisUntilFinished ) {
        return String.format( "%02d:%02d",
                MILLISECONDS.toMinutes( millisUntilFinished ) - HOURS.toMinutes( MILLISECONDS.toHours( millisUntilFinished ) ),
                MILLISECONDS.toSeconds( millisUntilFinished ) - MINUTES.toSeconds( MILLISECONDS.toMinutes( millisUntilFinished ) )
        );
    }
}
