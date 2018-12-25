package com.dualnback.dao;

import com.dualnback.io.FileIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.dualnback.game.NBackVersion.TwoBack;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class FileBasedDaoIntegrationTest {

    private final String TEST_RESOURCES_DIR = "app/src/test/resources";
    private File file;

    @Before
    public void setUp( ) {
        try {
            file = File.createTempFile( "testfile", ".json", new File( TEST_RESOURCES_DIR ) );
        } catch ( IOException e ) {
            fail( "Unable to create a temp file for testing. Bailing." );
        }
    }

    @After
    public void cleanUp( ) {
        if ( !file.delete() ) {
            fail( "Unable to delete temp file create for testing." );
        }
    }

    @Test
    public void testWriteEmptyDataSet( ) throws Exception {

        FileIO fileIO = new FileIO( file );

        FileBasedDao fileBasedDao = new FileBasedDao( fileIO );

        DataPointCollection dataPointCollection = new DataPointCollection( new ArrayList<DataPoint>() );

        fileBasedDao.write( dataPointCollection );

        DataPointCollection readData = fileBasedDao.read();

        assertThat( readData.size() ).isEqualTo( 0 );
    }

    @Test
    public void testWriteNonEmptyDataSet( ) throws Exception {

        FileIO fileIO = new FileIO( file );

        FileBasedDao fileBasedDao = new FileBasedDao( fileIO );

        Date now = new Date();
        ArrayList<DataPoint> userDataPoints = new ArrayList<>();
        userDataPoints.add( new DataPoint( now, 1, TwoBack ) );
        userDataPoints.add( new DataPoint( now, 100, TwoBack ) );

        DataPointCollection dataPointCollection = new DataPointCollection( userDataPoints );

        fileBasedDao.write( dataPointCollection );

        DataPointCollection readData = fileBasedDao.read();

        assertThat( readData.size() ).isEqualTo( userDataPoints.size() );
    }

}