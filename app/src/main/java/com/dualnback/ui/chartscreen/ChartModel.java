package com.dualnback.ui.chartscreen;


import android.util.Log;

import com.dualnback.data.filesystem.dao.ChartData;
import com.dualnback.data.filesystem.dao.Dao;
import com.dualnback.data.filesystem.dao.DataFileUtil;
import com.dualnback.data.filesystem.dao.DataPoint;
import com.dualnback.data.filesystem.dao.DataPointCollection;
import com.dualnback.data.filesystem.dao.FileBasedDao;
import com.dualnback.data.filesystem.io.FileIO;
import com.dualnback.data.filesystem.util.FileUtil;

import java.io.File;

import static com.dualnback.data.filesystem.dao.DataDtoConversion.convertToChartData;

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
