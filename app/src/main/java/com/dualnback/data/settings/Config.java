package com.dualnback.data.settings;


interface Config {

    int getMinScoreToAdvance( );

    int getMinScoreToFallback( );

    int vibrationLength( );

    long singleTrialDurationInMillis( );

}
