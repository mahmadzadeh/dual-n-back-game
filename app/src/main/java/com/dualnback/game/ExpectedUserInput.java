package com.dualnback.game;

public class ExpectedUserInput {

    private final UserInput soundMatch;

    private final UserInput locationMatch;

    public ExpectedUserInput( UserInput soundMatch, UserInput locationMatch ) {

        this.soundMatch = soundMatch;
        this.locationMatch = locationMatch;
    }

    public boolean isSoundMatch( UserInput soundMatch ) {
        return this.soundMatch == soundMatch;
    }


    public boolean isLocationMatch( UserInput locationMatch ) {
        return this.locationMatch == locationMatch;
    }

    public UserInput getSoundMatch( ) {
        return soundMatch;
    }

    public UserInput getLocationMatch( ) {
        return locationMatch;
    }

    @Override
    public String toString( ) {
        return "ExpectedUserInput{" +
                "soundMatch=" + soundMatch +
                ", locationMatch=" + locationMatch +
                '}';
    }
}
