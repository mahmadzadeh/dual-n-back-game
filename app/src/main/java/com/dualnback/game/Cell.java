package com.dualnback.game;

public class Cell {

    private int onImage;
    private int offImage;
    private int currentState;

    public Cell( int onImageResourceId, int offImageResourceId ) {
        this.onImage = onImageResourceId;
        this.offImage = offImageResourceId;
        this.currentState = offImageResourceId;
    }

    public boolean isTurnedOn( ) {
        return currentState == onImage;
    }

    public int turnOff( ) {
        currentState = offImage;
        return currentState;
    }

    public int turnOn( ) {
        currentState = onImage;
        return currentState;
    }

    public int getCurrentState( ) {
        return this.currentState;
    }

    @Override
    public String toString( ) {
        return "Cell{" +
                "currentState is off ?" + ( currentState == offImage ) +
                '}';
    }

}
