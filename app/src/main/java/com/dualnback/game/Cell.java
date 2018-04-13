package com.dualnback.game;

import android.widget.ImageView;

public class Cell {

    private int onImage;
    private int offImage;
    private int currentState;
    private ImageView backgroundImage;

    public Cell( int onImageResourceId, int offImageResourceId, ImageView backgroundImg ) {
        this.onImage = onImageResourceId;
        this.offImage = offImageResourceId;
        this.currentState = offImageResourceId;
        this.backgroundImage = backgroundImg;
    }

    public boolean isTurnedOn( ) {
        return currentState == onImage;
    }

    public void turnOff( ) {
        currentState = offImage;
        backgroundImage.setImageResource( offImage );
    }

    public void turnOn( ) {
        currentState = onImage;
        backgroundImage.setImageResource( onImage );
    }

    @Override
    public String toString( ) {
        return "Cell{" +
                "currentState is off ?" + ( currentState == offImage ) +
                '}';
    }

}
