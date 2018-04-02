package com.dualnback.game;

import org.junit.Test;
import org.mockito.Mock;

import java.util.Iterator;

import static org.junit.Assert.assertNotNull;


public class DualBackGameTest {

    @Mock
    DualBackGrid dualBackGrid;

    @Mock
    GameTrialCollection gameTrialCollection;

    Alternative alternative;

    @Test
    public void createInstance( ) {
        Alternative alternative = new Alternative( dualBackGrid, gameTrialCollection );
    }

    @Test
    public void givenInstanceThenGetNextSoundLocationReturns( ) {
        Trial trial = new Alternative( dualBackGrid, gameTrialCollection ).getNextTrial();

        assertNotNull( trial );
    }

    private class Alternative {

        private final DualBackGrid dualBackGrid;
        private final GameTrialCollection gameTrialCollection;
        private final Iterator<Trial> trialIterator;

        public Alternative( DualBackGrid dualBackGrid, GameTrialCollection gameTrialCollection ) {

            this.dualBackGrid = dualBackGrid;
            this.gameTrialCollection = gameTrialCollection;
            this.trialIterator = gameTrialCollection.iterator();
        }

        public Trial getNextTrial( ) {
            return trialIterator.hasNext() ? trialIterator.next() : null;
        }
    }
}