package com.dualnback.ui.startscreen.util;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.dualnback.ui.startscreen.StartScreenActivity;


public class StartScreenActivityIntentUtil {

    public static void backToStartScreen( View view, Activity activity ) {
        Intent startScreenIntent = new Intent( view.getContext(), StartScreenActivity.class );

        startScreenIntent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );

        activity.startActivity( startScreenIntent );
    }
}
