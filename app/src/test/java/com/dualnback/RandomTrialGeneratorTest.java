package com.dualnback;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomTrialGeneratorTest {

    @Test
    public void canCreateInstance() {
        RandomTrialGenerator generator = new RandomTrialGenerator();
    }

    @Test
    public void generateNextTrial() {
        RandomTrialGenerator generator = new RandomTrialGenerator();

        Trial trial = generator.nextTrial();

        assertNotNull( trial );
    }
}
