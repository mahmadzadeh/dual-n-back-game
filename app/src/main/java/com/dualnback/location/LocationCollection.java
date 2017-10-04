package com.dualnback.location;

import com.dualnback.IntegerRange;
import com.dualnback.random.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LocationCollection {

    private List<Location> locations = new ArrayList<>();

    public LocationCollection( ) {

        locations.add( 0, new Location( 0, 0 ) );
        locations.add( 0, new Location( 0, 1 ) );
        locations.add( 0, new Location( 0, 2 ) );
        locations.add( 0, new Location( 1, 0 ) );
        locations.add( 0, new Location( 1, 1 ) );
        locations.add( 0, new Location( 1, 2 ) );
        locations.add( 0, new Location( 2, 0 ) );
        locations.add( 0, new Location( 2, 1 ) );
        locations.add( 0, new Location( 2, 2 ) );

    }

    public Location getRandomLocation( ) {
        int randomIndex = RandomNumberGenerator.next( new IntegerRange( 0, this.locations.size() - 1 ) );

        return locations.get( randomIndex );
    }

    public List<Location> allLocations( ) {

        return new ArrayList<>( locations );
    }

    public int getSize( ) {

        return locations.size();

    }
}
