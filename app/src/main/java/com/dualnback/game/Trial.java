package com.dualnback.game;


import com.dualnback.data.location.Location;
import com.dualnback.data.sound.Sound;

public class Trial {

    private final Location location;
    private final Sound sound;

    private ExpectedUserInput userInput;

    public Trial( Location location, Sound sound ) {
        this.location = location;
        this.sound = sound;
    }

    public Location getLocation( ) {
        return location;
    }

    public Sound getSound( ) {
        return sound;
    }

    public ExpectedUserInput getUserInput( ) {
        return userInput;
    }

    public void setUserInput( ExpectedUserInput userInput ) {
        if ( userInput == null ) {
            throw new IllegalArgumentException( "Setting user input in Trial object to null is not permitted" );
        }

        this.userInput = userInput;
    }

    @Override
    public String toString( ) {
        return "Trial{" +
                "location=" + location +
                ", sound=" + sound +
                ", userInput=" + userInput +
                '}';
    }
}
