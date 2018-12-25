package com.dualnback.model;


import android.util.Log;

import com.dualnback.dao.ChartData;
import com.dualnback.dao.Dao;
import com.dualnback.dao.DataFileUtil;
import com.dualnback.dao.DataPoint;
import com.dualnback.dao.DataPointCollection;
import com.dualnback.dao.FileBasedDao;
import com.dualnback.io.FileIO;
import com.dualnback.util.FileUtil;

import java.io.File;

import static com.dualnback.dao.DataDtoConversion.convertToChartData;

public class ChartModel {
    private final File directory;
    private DataPointCollection dataPointCollection;
    private Dao dao;

    public ChartModel( File filesDirectory ) {
        directory = filesDirectory;

        File file = FileUtil.getDataFile( directory );

        dao = new FileBasedDao( new FileIO( file ) );

        dataPointCollection = DataFileUtil.readAllDataSortedByDate( directory );
    }

    public DataPointCollection getDataPoints( ) {
        return dataPointCollection;
    }

    public void addDataPoint( DataPoint newDataPoint ) {

        Log.e( "ChartModel", "ChartModel dataPointCollection.size() " + dataPointCollection.size() );


        dataPointCollection = dataPointCollection.addDataPoint( newDataPoint );
    }

    public ChartData chartData( ) {
        return convertToChartData( dataPointCollection );
    }

    public void saveData( ) {
        dao.write( dataPointCollection.shrinkDataSize() );
    }

}
