package com.dualnback.game;

import com.dualnback.random.RandomTrialGenerator;

import java.util.ArrayList;
import java.util.List;

class GameTrialCollection {

    protected static final int DEFAULT_TRIALS_COUNT = 24;

    private final List<Trial> trials = new ArrayList<>();

    private final NBackVersion version;
    private final RandomTrialGenerator randomTrialGenerator;
    private final int trialsCnt;

    public GameTrialCollection( NBackVersion version, RandomTrialGenerator randomTrialGenerator ) {
        this( version, DEFAULT_TRIALS_COUNT, randomTrialGenerator );
    }

    public GameTrialCollection( NBackVersion version, int trialsCount, RandomTrialGenerator randomTrialGenerator ) {
        this.version = version;
        this.randomTrialGenerator = randomTrialGenerator;
        this.trialsCnt = trialsCount;

        initTrials();
    }

    public List<Trial> getTrials( ) {
        return new ArrayList<>( trials );
    }

    private void initTrials( ) {
        for ( int i = 0; i < trialsCnt; i++ ) {
            this.trials.add( randomTrialGenerator.nextTrial() );
        }
    }
}
