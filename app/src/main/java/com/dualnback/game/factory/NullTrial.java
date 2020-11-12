package com.dualnback.game.factory;

import com.dualnback.game.ExpectedUserInput;
import com.dualnback.game.Trial;
import com.dualnback.game.UserInput;

public class NullTrial extends Trial {

    public NullTrial( ) {
        super( null, null );
        setUserInput( new ExpectedUserInput( UserInput.NoInput, UserInput.NoInput ) );
    }

    public NullTrial( String s ) {
        super( null, null );
        setUserInput( new ExpectedUserInput( UserInput.NoInput, UserInput.NoInput ) );
    }


}
