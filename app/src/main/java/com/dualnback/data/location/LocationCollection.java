package com.dualnback.data.location;

import com.dualnback.data.util.random.RandomNumberGenerator;
import com.dualnback.util.IntegerRange;

import java.util.ArrayList;
import java.util.List;

public class LocationCollection {

    private List<Location> locations = new ArrayList<>();

    public LocationCollection( ) {

        locations.add( new Location( 0, 0 ) );
        locations.add( new Location( 1, 0 ) );
        locations.add( new Location( 2, 0 ) );
        locations.add( new Location( 0, 1 ) );
        locations.add( new Location( 1, 1 ) );
        locations.add( new Location( 2, 1 ) );
        locations.add( new Location( 0, 2 ) );
        locations.add( new Location( 1, 2 ) );
        locations.add( new Location( 2, 2 ) );

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
