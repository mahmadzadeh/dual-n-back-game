package com.dualnback.model;

import com.dualnback.game.Cell;
import com.dualnback.game.DualBackGame;
import com.dualnback.game.Trial;
import com.dualnback.location.Location;

import java.util.Optional;

public class MainActivityModel implements MainModelContract.Model {

    private final DualBackGame dualBackGame;

    public MainActivityModel( final DualBackGame dualBackGame ) {
        this.dualBackGame = dualBackGame;
    }

    @Override
    public boolean recordLocationMatch( ) {
        return dualBackGame.recordLocationMatch();
    }

    @Override
    public boolean recordSoundMatch( ) {
        return dualBackGame.recordSoundMatch();
    }

    @Override
    public Optional<Cell> markEndOfTrial( ) {
        return dualBackGame.markEndOfTrial();
    }

    @Override
    public Optional<Cell> markStartOfTrial( ) {
        return dualBackGame.markStartOfTrial();
    }

    @Override
    public double getCurrentScore( ) {
        return dualBackGame.getCurrentScore();
    }

    @Override
    public double currentPoints( ) {
        return dualBackGame.getCurrentScore();
    }

    @Override
    public Optional<Location> findCellLocation( Cell cell ) {
        return dualBackGame.findCellLocation( cell );
    }

    @Override
    public Optional<Trial> getCurrentTrial( ) {
        return dualBackGame.getCurrentTrial();
    }

}
