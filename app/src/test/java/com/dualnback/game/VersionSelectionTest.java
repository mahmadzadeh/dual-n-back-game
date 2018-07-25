package com.dualnback.game;


import com.dualnback.dao.Dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class VersionSelectionTest {

    @Mock
    private Dao mockDao;

    private VersionSelection versionSelection;

    @Before
    public void setUp( ) {
        versionSelection = new VersionSelection( mockDao );
    }


    @Test
    public void givenNoPreviousGamesPlayedThenLevelDefaultsToTwo( ) {
        assertThat( versionSelection.currentLevel() ).isEqualTo( NBackVersion.TwoBack );
    }

    @Test
    public void givenOnePreviousGameThenItsScoreIsUsedToDetermineNextGameLevel( ) {
        assertThat( versionSelection.currentLevel() ).isEqualTo( NBackVersion.ThreeBack );
    }


}
