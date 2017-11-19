package com.dualnback;

import com.dualnback.location.Location;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LocationToImageMapperTest {

    @Test
    public void mapMethodWorks( ) throws Exception {
        assertEquals ( R.id.image_00 , LocationToImageMapper.map( new Location( 0, 0 ) ));
        assertEquals ( R.id.image_10 , LocationToImageMapper.map( new Location( 1, 0 ) ));
        assertEquals ( R.id.image_22 , LocationToImageMapper.map( new Location( 2, 2 ) ));
    }

}