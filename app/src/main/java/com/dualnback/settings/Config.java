package com.dualnback.settings;


interface Config {

    int getMinScoreToAdvance( );

    int getMinScoreToFallback( );

    int vibrationLength( );

    long singleTrialDurationInMillis( );

}
