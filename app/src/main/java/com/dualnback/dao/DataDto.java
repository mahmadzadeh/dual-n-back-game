package com.dualnback.dao;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.dualnback.util.DtoJSONConversion.dataDtoToJSON;


public class DataDto {

    public static final int MAX_DATA_POINT_SIZE = 40;
    private List<DataPoint> userDataPoints;

    public DataDto( List<DataPoint> userDataPoints ) {
        this.userDataPoints = userDataPoints;
    }

    public List<DataPoint> userDataPoints( ) {
        return userDataPoints;
    }

    public int size( ) {
        return userDataPoints.size();
    }

    public DataDto addDataPoint( DataPoint dataPoint ) {
        List<DataPoint> copy = new ArrayList<>( userDataPoints );
        copy.add( dataPoint );

        return new DataDto( copy );
    }

    public String toJSON( ) {
        try {
            return dataDtoToJSON( this ).toString();
        } catch ( JSONException e ) {
            return ""; // not the end of the world if this can't be represented as JSON
        }
    }

    public DataDto sortedDataPoints( ) {
        List<DataPoint> sortedCopy = new ArrayList<>( userDataPoints );

        Collections.sort( sortedCopy );

        return new DataDto( sortedCopy );
    }

    // if size is too large then remove the oldest item from list
    public DataDto shrinkDataSize( ) {
        return userDataPoints.size() > MAX_DATA_POINT_SIZE
                ? new DataDto( new ArrayList<>( this.userDataPoints.subList( 1, MAX_DATA_POINT_SIZE + 1 ) ) )
                : this;
    }
}
