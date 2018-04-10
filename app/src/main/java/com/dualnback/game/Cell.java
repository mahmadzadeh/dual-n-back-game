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

    public void turnOff( ) {
        currentState = offImage;
    }

    public void turnOn( ) {
        currentState = onImage;
    }

    @Override
    public String toString( ) {
        return "Cell{" +
                "currentState is off ?" + ( currentState == offImage ) +
                '}';
    }
}
