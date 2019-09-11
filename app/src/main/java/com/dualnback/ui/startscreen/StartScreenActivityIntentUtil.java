package com.dualnback.ui.startscreen;

import android.app.Activity;
import android.content.Intent;
import android.view.View;


public class StartScreenActivityIntentUtil {

    public static void backToStartScreen( View view, Activity activity ) {
        Intent startScreenIntent = new Intent( view.getContext(), StartScreenActivity.class );

        startScreenIntent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );

        activity.startActivity( startScreenIntent );
    }
}
