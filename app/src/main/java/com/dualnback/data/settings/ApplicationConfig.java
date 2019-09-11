package com.dualnback.data.settings;


public class ApplicationConfig implements Config {

    protected static final String VIBRATION_DEFAULT_MILLISECONDS = "100";
    protected static final String VIBRATION_DURATION_LIST_KEY = "vibration_duration_list";

    protected static final String SINGLE_TRIAL_LENGTH_KEY = "trial_length";
    protected static final int TRIAL_LENGTH_DEFAULT_IN_SECONDS = 3;

    protected static final String MIN_TRIAL_ADVANCE_SCORE_KEY = "min_advance_score";
    protected static final int MIN_TRIAL_ADVANCE_DEFAULT = 85;

    private final IConfigReader reader;

    public ApplicationConfig( IConfigReader reader ) {
        this.reader = reader;
    }

    @Override
    public int getMinScoreToAdvance( ) {
        return reader.getIntPreference( MIN_TRIAL_ADVANCE_SCORE_KEY, MIN_TRIAL_ADVANCE_DEFAULT );
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
    public long singleTrialDurationInMillis( ) {
        return reader.getIntPreference( SINGLE_TRIAL_LENGTH_KEY, TRIAL_LENGTH_DEFAULT_IN_SECONDS ) * 1000;
    }
}
