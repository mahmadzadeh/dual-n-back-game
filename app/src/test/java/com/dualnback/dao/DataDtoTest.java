package com.dualnback.dao;

import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.dualnback.util.JSONUtil.parse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DataDtoTest {

    @Test
    public void testToJSONWithEmptyDataPointList( ) throws JSONException {
        List<DataPoint> dataPointList = new ArrayList<>();

        String json = new DataDto( dataPointList ).toJSON();

        assertThat( parse( json ).size(), is( equalTo( 0 ) ) );
    }

    @Test
    public void testToJSONWithDataPointList( ) throws JSONException {
        List<DataPoint> dataPointList = Arrays.asList( new DataPoint( new Date(), 2 ) );

        String json = new DataDto( dataPointList ).toJSON();

        assertThat( parse( json ).size(), is( equalTo( 1 ) ) );
    }

    @Test
    public void sortUserDataPointsBasedOnDataPointDate( ) throws JSONException {
        Date today = new Date();
        Date yesterdayThisTime = new Date( today.getTime() - 86400000l );
        Date twoDaysAgoThisTime = new Date( yesterdayThisTime.getTime() - 86400000l );

        List<DataPoint> dataPointList = Arrays.asList(
                new DataPoint( today, 10 ),
                new DataPoint( twoDaysAgoThisTime, 20 ),
                new DataPoint( yesterdayThisTime, 30 )
        );

        DataDto dataDto = new DataDto( dataPointList );

        List<DataPoint> sorted = dataDto.sortedDataPoints().userDataPoints();

        assertThat( sorted.get( 0 ).score(), is( equalTo( 20 ) ) );
        assertThat( sorted.get( 1 ).score(), is( equalTo( 30 ) ) );
        assertThat( sorted.get( 2 ).score(), is( equalTo( 10 ) ) );
    }

    @Test
    public void shrinkDataSizeReturnsInstanceWithSmallerDataSize( ) {

        int size = DataDto.MAX_DATA_POINT_SIZE + 1;
        List<DataPoint> dataPointList = getDataPointListOfSize( size );

        DataDto dataDto = new DataDto( dataPointList );

        DataDto shrunkDataDto = dataDto.shrinkDataSize();

        assertThat( shrunkDataDto.userDataPoints().size(), is( equalTo( DataDto.MAX_DATA_POINT_SIZE ) ) );
    }

    @Test
    public void givenDataSizeSmallerThandMaxThenDataSizeStaysSame( ) {

        int size = DataDto.MAX_DATA_POINT_SIZE - 1;
        List<DataPoint> dataPointList = getDataPointListOfSize( size );

        DataDto dataDto = new DataDto( dataPointList );

        DataDto shrunkDataDto = dataDto.shrinkDataSize();

        assertThat( shrunkDataDto.userDataPoints().size(), is( equalTo( size ) ) );
    }

    @Test
    public void givenDataSizeEqualToMaxThenDataSizeStaysSame( ) {

        int size = DataDto.MAX_DATA_POINT_SIZE;
        List<DataPoint> dataPointList = getDataPointListOfSize( size );

        DataDto dataDto = new DataDto( dataPointList );

        DataDto shrunkDataDto = dataDto.shrinkDataSize();

        assertThat( shrunkDataDto.userDataPoints().size(), is( equalTo( size ) ) );
    }

    private List<DataPoint> getDataPointListOfSize( int size ) {
        List<DataPoint> dataPointList = new ArrayList<>();

        for ( int i = 0; i < size; ++i ) {
            dataPointList.add( new DataPoint( new Date(), 10 ) );
        }

        return dataPointList;
    }
}