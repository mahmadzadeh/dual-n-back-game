package com.dualnback;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.TextView;


public class CountDownText {


    public static final int TIME_FOR_TEXT_COLOUR_CHANGE = 10;
    public static final int COUNT_DOWN_WARNING_COLOUR = Color.YELLOW;
    public static final int TIME_TEXT_NORMAL_COLOUR = Color.WHITE;

    private final TextView textView;

    public CountDownText( TextView textView ) {

        this.textView = textView;
    }

    public void setText( String now ) {

        if ( isTimeToChangeTimeTxtColour( now ) ) {
            textView.setTextColor( COUNT_DOWN_WARNING_COLOUR );
        } else {
            textView.setTextColor( TIME_TEXT_NORMAL_COLOUR );
        }

        textView.setText( now );

    }

    private boolean isTimeToChangeTimeTxtColour( String now ) {
        return getSecondsLeft( now ) <= TIME_FOR_TEXT_COLOUR_CHANGE;
    }

    @NonNull
    private Integer getSecondsLeft( String time ) {

        String[] split = time.split( ":" );
        return ( Integer.valueOf( split[ 0 ] ) * 60 ) + Integer.valueOf( split[ 1 ] );

    }


}
