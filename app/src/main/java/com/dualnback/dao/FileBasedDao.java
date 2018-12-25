package com.dualnback.dao;

import android.util.Log;

import com.dualnback.io.FileIO;
import com.dualnback.io.FileIOException;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.dualnback.util.JSONUtil.parse;


public class FileBasedDao implements Dao {

    private FileIO fileIO;

    public FileBasedDao( FileIO fileIO ) {
        this.fileIO = fileIO;
    }

    @Override
    public DataPointCollection read( ) {
        List<DataPoint> dataPoints = new ArrayList<>();

        try {

            dataPoints.addAll( parse( fileIO.read() ) );

        } catch ( FileIOException e ) {
            Log.e( "PARSE_ERROR", e.getMessage() );
        } catch ( JSONException e ) {
            Log.e( "JSON_ERROR", e.getMessage() );
        }

        return new DataPointCollection( dataPoints );
    }

    @Override
    public void write( DataPointCollection dataPointCollection ) {

        String JSON = dataPointCollection.toJSON();

        try {

            fileIO.write( JSON );

        } catch ( FileIOException e ) {
            Log.e( "FILE_IO_EXCEPTION", e.getMessage() );
        }
    }
}
