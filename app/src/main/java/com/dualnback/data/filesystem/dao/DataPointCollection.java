package com.dualnback.data.filesystem.dao;

import android.util.Log;

import org.apache.commons.collections4.list.UnmodifiableList;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.dualnback.data.filesystem.util.DtoJSONConversion.dataDtoToJSON;


public class DataPointCollection {

    public static final int MAX_DATA_POINT_SIZE = 40;
    private List<DataPoint> userDataPoints;

    public DataPointCollection( List<DataPoint> userDataPoints ) {
        this.userDataPoints = new ArrayList<>( userDataPoints );
    }

    public List<DataPoint> userDataPoints( ) {
        return new UnmodifiableList<>( userDataPoints );
    }

    public int size( ) {
        return userDataPoints.size();
    }

    public DataPointCollection addDataPoint( DataPoint dataPoint ) {
        List<DataPoint> copy = new ArrayList<>( userDataPoints );
        copy.add( dataPoint );

        return new DataPointCollection( copy );
    }

    public String toJSON( ) {
        try {
            return dataDtoToJSON( this ).toString();
        } catch ( JSONException e ) {
            return ""; // not the end of the world if this can't be represented as JSON
        }
    }

    public DataPointCollection sortedDataPoints( ) {
        List<DataPoint> sortedCopy = new ArrayList<>( userDataPoints );

        Collections.sort( sortedCopy );

        return new DataPointCollection( sortedCopy );
    }

    public Optional<DataPoint> getLastDataPoint( ) {

        DataPointCollection sortedDataByDate = sortedDataPoints();

        return Optional.ofNullable(
                sortedDataByDate.size() == 0
                        ? null
                        : sortedDataByDate.userDataPoints().get( sortedDataByDate.size() - 1 ) );
    }

    // if size is too large then remove the oldest item from list
    public DataPointCollection shrinkDataSize( ) {
        Log.e( "DataPointCollection", "userDataPoints.size()" + userDataPoints.size() );

        return userDataPoints.size() > MAX_DATA_POINT_SIZE
                ? new DataPointCollection( new ArrayList<>( this.userDataPoints.subList( 1, MAX_DATA_POINT_SIZE + 1 ) ) )
                : this;
    }
}
