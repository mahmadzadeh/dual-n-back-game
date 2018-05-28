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
    public DataDto read( ) {
        List<DataPoint> dataPoints = new ArrayList<>();

        try {

            dataPoints.addAll( parse( fileIO.read() ) );

        } catch ( FileIOException e ) {
            Log.e( "PARSE_ERROR", e.getMessage() );
        } catch ( JSONException e ) {
            Log.e( "JSON_ERROR", e.getMessage() );
        }

        return new DataDto( dataPoints );
    }

    @Override
    public void write( DataDto dataDto ) {

        String JSON = dataDto.toJSON();

        try {

            fileIO.write( JSON );

        } catch ( FileIOException e ) {
            Log.e( "FILE_IO_EXCEPTION", e.getMessage() );
        }
    }
}
