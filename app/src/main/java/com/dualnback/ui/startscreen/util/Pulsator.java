package com.dualnback.ui.startscreen.util;


import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.widget.ImageView;

public class Pulsator {

    private static final int DURATION = 310;
    private static final float X_SCALE = 1.2f;
    private static final float Y_SCALE = 1.2f;

    public static void pulsate( ImageView imageView ) {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat( "scaleX", X_SCALE ),
                PropertyValuesHolder.ofFloat( "scaleY", Y_SCALE ) );

        scaleDown.setDuration( DURATION );

        scaleDown.setRepeatCount( ObjectAnimator.INFINITE );
        scaleDown.setRepeatMode( ObjectAnimator.REVERSE );

        scaleDown.start();
    }

}
