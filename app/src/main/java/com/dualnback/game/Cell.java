package com.dualnback.game;

import com.dualnback.MainScreenView;

public class Cell {

    private int onImage;
    private int offImage;
    private int currentState;
    private MainScreenView view;

    public Cell( int onImageResourceId, int offImageResourceId, MainScreenView view ) {
        this.onImage = onImageResourceId;
        this.offImage = offImageResourceId;
        this.currentState = offImageResourceId;
        this.view = view;
    }

    public boolean isTurnedOn( ) {
        return currentState == onImage;
    }

    public void turnOff( ) {
        currentState = offImage;
        view.swapImage( this, currentState );
    }

    public void turnOn( ) {
        currentState = onImage;
        view.swapImage( this, currentState );
    }

    @Override
    public String toString( ) {
        return "Cell{" +
                "currentState is off ?" + ( currentState == offImage ) +
                '}';
    }

}
