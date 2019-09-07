package com.dualnback.util;

import com.dualnback.dao.DataPoint;
import com.dualnback.io.FileIO;
import com.dualnback.io.FileIOException;

import org.json.JSONException;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.dualnback.util.JSONUtil.DEFAULT_N_BACK_VERSION;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;


public class JSONUtilTest {

    private static final String TEST_RESOURCES_DIR = "src/test/resources/";

    @Test
    public void givenJSONStringNoDataThenParsReturnsEmptyListOfDataPoints( ) throws FileIOException, JSONException {

        String JSON = readTestFile( "emptySampleFile.json" );

        List<DataPoint> dataPointList = JSONUtil.parse( JSON );

        assertTrue( dataPointList.isEmpty() );
    }

    @Test
    public void givenJSONStringWithOneDataPointThenParsReturnsListOfOneDataPoints( ) throws FileIOException, JSONException {

        String JSON = readTestFile( "oneDataPointSampleFile.json" );

        List<DataPoint> dataPointList = JSONUtil.parse( JSON );

        DataPoint dataPoint = dataPointList.get( 0 );
        String expectedDateFormatted = new SimpleDateFormat( DateUtil.FORMAT_PATTERN ).format( dataPoint.date() );

        assertThat( expectedDateFormatted ).isEqualTo( "2012-04-23T18:25:43" );
        assertThat( dataPoint.score() ).isEqualTo( 2 );
    }

    @Test
    public void givenJSONStringWithMultipleDataPointThenParsReturnsListOfOneDataPoints( ) throws FileIOException, JSONException {

        String JSON = readTestFile( "sampleFile.json" );

        List<DataPoint> dataPointList = JSONUtil.parse( JSON );

        assertThat( dataPointList.size() ).isEqualTo( 4 );
    }

    /**
     * TODO: this seems odd for the parser to set a default value. Will have to be refactored
     */
    @Test
    public void givenNoValidVersionPresentInJsonThenParseSetsToDefaultGameVersion( ) throws FileIOException, JSONException {

        String JSON = readTestFile( "oneDataPointMissingVersion.json" );

        List<DataPoint> dataPointList = JSONUtil.parse( JSON );

        assertThat( dataPointList.size() ).isEqualTo( 1 );

        assertThat( dataPointList.get( 0 ).version() ).isEqualTo( DEFAULT_N_BACK_VERSION );
    }

    private String readTestFile( String testFileName ) throws FileIOException {
        return new FileIO( new File( TEST_RESOURCES_DIR + testFileName ) ).read();
    }


}