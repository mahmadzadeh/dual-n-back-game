package com.dualnback.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DataFileUtilTest {


    private static final String TEST_RESOURCES_DIR = "app/src/test/resources";

    @Test
    public void givenDataFileDirThenReadAllDataReadsIt( ) {

        DataDto dataDto = DataFileUtil.readAllData( new File( TEST_RESOURCES_DIR ) );

        assertThat( dataDto.size() ).isEqualTo( 2 );
    }

    @Test
    public void givenDataFileDirThenReadAllSortedDataReadsItInSortedOrder( ) {

        DataDto dataDto = DataFileUtil.readAllDataSortedByDate( new File( TEST_RESOURCES_DIR ) );

        assertThat( dataDto.size() ).isEqualTo( 2 );
        assertThat( dataDto.userDataPoints().get( 0 ).score() ).isEqualTo( 2 );
        assertThat( dataDto.userDataPoints().get( 1 ).score() ).isEqualTo( 80 );
    }
}