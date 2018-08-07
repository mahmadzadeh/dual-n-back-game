package com.dualnback.settings;


public class ApplicationConfig implements Config {

    protected static final String VIBRATION_DEFAULT_MILLISECONDS = "100";
    protected static final String VIBRATION_DURATION_LIST_KEY = "vibration_duration_list";
    private static final String DEF_VALUE = "";

    private final ConfigReader reader;

    public ApplicationConfig( ConfigReader reader ) {
        this.reader = reader;
    }

    @Override
    public int getMinScoreToAdvance( ) {
        return 0;
    }

    @Override
    public int getMinScoreToFallback( ) {
        return 0;
    }

    @Override
    public int vibrationLength( ) {
        return reader.getStringPreferenceWithCastToInt( VIBRATION_DURATION_LIST_KEY, VIBRATION_DEFAULT_MILLISECONDS );
    }

    @Override
    public int singleRoundDuration( ) {
        return 0;
    }
}
