package com.dualnback.util;

import com.dualnback.dao.DataPoint;
import com.dualnback.io.FileIO;
import com.dualnback.io.FileIOException;

import org.json.JSONException;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class JSONUtilTest {

    private static final String TEST_RESOURCES_DIR = "app/src/test/resources/";

    @Test
    public void givenJSONStringNoDataThenParsReturnsEmptyListOfDataPoints( ) throws FileIOException, JSONException {

        String JSON = new FileIO( new File( TEST_RESOURCES_DIR + "emptySampleFile.json" ) ).read();

        List<DataPoint> dataPointList = JSONUtil.parse( JSON );

        assertTrue( dataPointList.isEmpty() );
    }

    @Test
    public void givenJSONStringWithOneDataPointThenParsReturnsListOfOneDataPoints( ) throws FileIOException, JSONException {

        String JSON = new FileIO( new File( TEST_RESOURCES_DIR + "oneDataPointSampleFile.json" ) ).read();

        List<DataPoint> dataPointList = JSONUtil.parse( JSON );

        DataPoint dataPoint = dataPointList.get( 0 );
        String expectedDateFormatted = new SimpleDateFormat( DateUtil.FORMAT_PATTERN ).format( dataPoint.date() );

        assertThat( expectedDateFormatted, is( equalTo( "2012-04-23T18:25:43" ) ) );
        assertThat( dataPoint.score(), is( equalTo( 2 ) ) );
    }

    @Test
    public void givenJSONStringWithMultipleDataPointThenParsReturnsListOfOneDataPoints( ) throws FileIOException, JSONException {

        String JSON = new FileIO( new File( TEST_RESOURCES_DIR + "sampleFile.json" ) ).read();

        List<DataPoint> dataPointList = JSONUtil.parse( JSON );

        assertThat( dataPointList.size(), is( equalTo( 4 ) ) );
    }
}