package com.dualnback.data.location;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mahmadzadeh on 31/03/18.
 */
public class LocationTest {

    @Test
    public void testObjectEquality( ) {

        Location loc1 = new Location( 0, 0 );
        Location loc2 = new Location( 0, 0 );

        assertEquals( loc1, loc2 );
        assertEquals( loc2, loc1 );
    }

    @Test
    public void testObjectNonEquality( ) {

        Location loc1 = new Location( 0, 0 );
        Location loc2 = new Location( 1, 0 );

        assertNotEquals( loc1, loc2 );
        assertNotEquals( loc2, loc1 );

        loc1 = new Location( 1, 1 );
        assertNotEquals( loc1, loc2 );

        loc1 = new Location( 2, 2 );
        assertNotEquals( loc1, loc2 );
    }

    @Test
    public void tesHashCode( ) {
        assertEquals( new Location( 0, 0 ).hashCode(), new Location( 0, 0 ).hashCode() );
    }


}