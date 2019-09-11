package com.dualnback.data.location;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationCollectionTest {

    @Test
    public void canCreateInstance( ) {
        new LocationCollection();
    }

    @Test
    public void givenInstanceThenCanGetRandomLocation( ) {
        Location location = new LocationCollection().getRandomLocation();

        assertNotNull( location );
    }

    @Test
    public void canGetAllLocations( ) {
        LocationCollection locationCollection = new LocationCollection();

        List<Location> location = locationCollection.allLocations();

        assertEquals( location.size(), locationCollection.getSize() );
    }
}