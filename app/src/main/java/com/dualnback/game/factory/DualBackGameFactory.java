package com.dualnback.game.factory;

import com.dualnback.data.util.random.RandomTrialGenerator;
import com.dualnback.game.DualBackGame;
import com.dualnback.game.DualBackGrid;
import com.dualnback.game.GameTrialCollection;
import com.dualnback.game.Trial;

import java.util.List;

import static com.dualnback.game.factory.TrialListFactory.updateListWithExpectedSoundAndLocationMatch;

public class DualBackGameFactory {

    public static DualBackGame create( GameParameters gameParameters ) {

        DualBackGrid instance = GridFactory.instance();

        GameTrialCollection gameTrialCollection = new GameTrialCollection(
                gameParameters.version(),
                getTrials( gameParameters ) );

        return new DualBackGame( instance, gameTrialCollection );

    }


    private static List<Trial> getTrials( GameParameters params ) {
        RandomTrialGenerator randomTrialGenerator = new RandomTrialGenerator( params.locCollection(), params.soundCollection() );

        List<Trial> originalTrialList = TrialListFactory.create( randomTrialGenerator );

        return updateListWithExpectedSoundAndLocationMatch( originalTrialList,
                params.getExpectedSoundMatches(),
                params.getExpectedLocMatches(),
                params.version() );
    }


}
