package com.dualnback;

import android.os.Handler;
import android.os.Message;

import com.dualnback.game.DualBackGame;
import com.dualnback.game.Trial;
import com.dualnback.game.factory.NullTrial;

import java.util.Optional;


public class GameMainThreadHandler extends Handler {

    private final DualBackGame dualBackGame;
    private Optional<Trial> currentTrial;

    public GameMainThreadHandler( DualBackGame dualBackGame ) {
        this.dualBackGame = dualBackGame;
        this.currentTrial = Optional.empty();
    }

    @Override
    public void handleMessage( Message msg ) {
        Trial trial = dualBackGame.markStartOfTrial();
        trial.getSound().playSound();

        currentTrial = Optional.of( trial );
    }

    public Trial getCurrentTrial( ) {
        return currentTrial.orElse( new NullTrial() );
    }
}
