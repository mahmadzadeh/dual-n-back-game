package com.dualnback.game;

import com.dualnback.SwappableImage;

public class Cell {

    private int onImage;
    private int offImage;
    private int currentState;
    private SwappableImage image;

    public Cell( int onImageResourceId, int offImageResourceId, SwappableImage backgroundImg ) {
        this.onImage = onImageResourceId;
        this.offImage = offImageResourceId;
        this.currentState = offImageResourceId;
        this.image = backgroundImg;
    }

    public boolean isTurnedOn( ) {
        return currentState == onImage;
    }

    public void turnOff( ) {
        currentState = offImage;
        image.swapImage( this, currentState );
    }

    public void turnOn( ) {
        currentState = onImage;
        image.swapImage( this, currentState );
    }

    @Override
    public String toString( ) {
        return "Cell{" +
                "currentState is off ?" + ( currentState == offImage ) +
                '}';
    }

}
