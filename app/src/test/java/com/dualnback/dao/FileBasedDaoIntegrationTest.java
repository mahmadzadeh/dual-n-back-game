package com.dualnback.dao;


import com.dualnback.io.FileIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class FileBasedDaoIntegrationTest {

    File file;

    @Before
    public void setUp( ) {
        try {
            file = File.createTempFile( "testfile", ".json", new File( "src/test/resources/" ) );
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

        DataDto dataDto = new DataDto( new ArrayList<DataPoint>() );

        fileBasedDao.write( dataDto );

        DataDto readData = fileBasedDao.read();

        assertThat( readData.size(), is( equalTo( 0 ) ) );
    }

    @Test
    public void testWriteNonEmptyDataSet( ) throws Exception {

        FileIO fileIO = new FileIO( file );

        FileBasedDao fileBasedDao = new FileBasedDao( fileIO );

        Date now = new Date();
        ArrayList<DataPoint> userDataPoints = new ArrayList<>();
        userDataPoints.add( new DataPoint( now, 1 ) );
        userDataPoints.add( new DataPoint( now, 100 ) );

        DataDto dataDto = new DataDto( userDataPoints );

        fileBasedDao.write( dataDto );

        DataDto readData = fileBasedDao.read();

        assertThat( readData.size(), is( equalTo( userDataPoints.size() ) ) );
    }

}