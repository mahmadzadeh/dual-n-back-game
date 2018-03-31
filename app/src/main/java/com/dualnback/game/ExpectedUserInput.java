package com.dualnback.game;

import java.util.Optional;

class ExpectedUserInput {

    private final Optional<UserInput> soundMatch;
    private final Optional<UserInput> locationMatch;

    public ExpectedUserInput( UserInput soundMatch, UserInput locationMatch ) {

        this.soundMatch = Optional.ofNullable( soundMatch );
        this.locationMatch = Optional.ofNullable( locationMatch );
    }


    public boolean isSoundMatch( UserInput soundMatch ) {
        return this.soundMatch.map( sm -> sm == soundMatch ).orElse( false );
    }

    public boolean isLocationMatch( UserInput locationMatch ) {
        return this.locationMatch.map( lm-> lm == locationMatch ).orElse( false );
    }
}
