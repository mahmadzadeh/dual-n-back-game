package com.dualnback.game;

import com.dualnback.R;
import com.dualnback.location.Location;

import java.util.HashMap;
import java.util.Map;

public class LocationToImageMapper {
    private final static Map<String, Integer> LOCATION_TO_IMAGE_MAP = new HashMap<String, Integer>() {{
        put( "00", R.id.image_00 );
        put( "01", R.id.image_01 );
        put( "02", R.id.image_02 );
        put( "10", R.id.image_10 );
        put( "11", R.id.image_11 );
        put( "12", R.id.image_12 );
        put( "20", R.id.image_20 );
        put( "21", R.id.image_21 );
        put( "22", R.id.image_22 );
    }};

    public static int map( Location location ) {
        String mapKey = buildMapKey( location );
        return LOCATION_TO_IMAGE_MAP.get( mapKey );
    }

    private static String buildMapKey( Location location ) {
        return location.getRow() + "" + location.getCol();
    }
}
