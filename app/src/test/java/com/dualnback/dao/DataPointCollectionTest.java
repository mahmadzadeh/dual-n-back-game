package com.dualnback.dao;

import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.dualnback.game.NBackVersion.TwoBack;
import static com.dualnback.util.JSONUtil.parse;
import static org.assertj.core.api.Assertions.assertThat;

public class DataPointCollectionTest {

    @Test
    public void testToJSONWithEmptyDataPointList( ) throws JSONException {
        List<DataPoint> dataPointList = new ArrayList<>();

        String json = new DataPointCollection( dataPointList ).toJSON();

        assertThat( parse( json ).size() ).isEqualTo( 0 );
    }

    @Test
    public void testToJSONWithDataPointList( ) throws JSONException {
        List<DataPoint> dataPointList = Arrays.asList( new DataPoint( new Date(), 2, TwoBack ) );

        String json = new DataPointCollection( dataPointList ).toJSON();

        assertThat( parse( json ).size() ).isEqualTo( 1 );
    }

    @Test
    public void sortUserDataPointsBasedOnDataPointDate( ) throws JSONException {
        Date today = new Date();
        Date yesterdayThisTime = new Date( today.getTime() - 86400000l );
        Date twoDaysAgoThisTime = new Date( yesterdayThisTime.getTime() - 86400000l );

        List<DataPoint> dataPointList = Arrays.asList(
                new DataPoint( today, 10, null ),
                new DataPoint( twoDaysAgoThisTime, 20, null ),
                new DataPoint( yesterdayThisTime, 30, null )
        );

        DataPointCollection dataPointCollection = new DataPointCollection( dataPointList );

        List<DataPoint> sorted = dataPointCollection.sortedDataPoints().userDataPoints();

        assertThat( sorted.get( 0 ).score() ).isEqualTo( 20 );
        assertThat( sorted.get( 1 ).score() ).isEqualTo( 30 );
        assertThat( sorted.get( 2 ).score() ).isEqualTo( 10 );
    }

    @Test
    public void shrinkDataSizeReturnsInstanceWithSmallerDataSize( ) {

        int size = DataPointCollection.MAX_DATA_POINT_SIZE + 1;
        List<DataPoint> dataPointList = getDataPointListOfSize( size );

        DataPointCollection dataPointCollection = new DataPointCollection( dataPointList );

        DataPointCollection shrunkDataPointCollection = dataPointCollection.shrinkDataSize();

        assertThat( shrunkDataPointCollection.userDataPoints().size() ).isEqualTo( DataPointCollection.MAX_DATA_POINT_SIZE );
    }

    @Test
    public void givenDataSizeSmallerThandMaxThenDataSizeStaysSame( ) {

        int size = DataPointCollection.MAX_DATA_POINT_SIZE - 1;
        List<DataPoint> dataPointList = getDataPointListOfSize( size );

        DataPointCollection dataPointCollection = new DataPointCollection( dataPointList );

        DataPointCollection shrunkDataPointCollection = dataPointCollection.shrinkDataSize();

        assertThat( shrunkDataPointCollection.userDataPoints().size() ).isEqualTo( size );
    }

    @Test
    public void givenDataSizeEqualToMaxThenDataSizeStaysSame( ) {

        int size = DataPointCollection.MAX_DATA_POINT_SIZE;
        List<DataPoint> dataPointList = getDataPointListOfSize( size );

        DataPointCollection dataPointCollection = new DataPointCollection( dataPointList );

        DataPointCollection shrunkDataPointCollection = dataPointCollection.shrinkDataSize();

        assertThat( shrunkDataPointCollection.userDataPoints().size() ).isEqualTo( size );
    }

    @Test
    public void givenDataSizeOfZeroThenGetLastGameDataPointReturnsOptionalEmpty( ) {

        int size = 0;

        DataPointCollection dataPointCollection = new DataPointCollection( getDataPointListOfSize( size ) );

        assertThat( dataPointCollection.getLastDataPoint() ).isEqualTo( Optional.empty() );
    }

    @Test
    public void givenDataSizeOfOneThenGetLastGameDataPointReturnsTheOnlyDataPoint( ) {

        int size = 1;

        List<DataPoint> dataPointListOfSize = getDataPointListOfSize( size );
        DataPointCollection dataPointCollection = new DataPointCollection( dataPointListOfSize );

        assertThat( dataPointCollection.getLastDataPoint().get() ).isEqualTo( dataPointListOfSize.get( 0 ) );
    }

    @Test
    public void givenDataSizeOfFiveThenGetLastGameDataPointReturnsLastDataPoint( ) {

        int size = 5;

        List<DataPoint> dataPoints = getDataPointListOfSize( size );
        DataPointCollection dataPointCollection = new DataPointCollection( dataPoints );

        assertThat( dataPointCollection.getLastDataPoint().get() )
                .isEqualTo( dataPoints.get( dataPoints.size() - 1 ) );
    }

    private List<DataPoint> getDataPointListOfSize( int size ) {
        List<DataPoint> dataPointList = new ArrayList<>();

        for ( int i = 0; i < size; ++i ) {
            dataPointList.add( new DataPoint( new Date(), 10, null ) );
        }

        return dataPointList;
    }

}