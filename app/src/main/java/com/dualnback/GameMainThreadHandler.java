package com.dualnback;

import android.os.Handler;
import android.os.Message;

import com.dualnback.game.DualBackGame;
import com.dualnback.game.Trial;

import java.util.Optional;


public class GameMainThreadHandler extends Handler {

    private final DualBackGame dualBackGame;

    public GameMainThreadHandler( DualBackGame dualBackGame ) {
        this.dualBackGame = dualBackGame;
    }

    @Override
    public void handleMessage( Message msg ) {
        Optional<Trial> trial = dualBackGame.getNextTrial();

        trial.ifPresent( t -> t.getSound().playSound() );
    }
}
