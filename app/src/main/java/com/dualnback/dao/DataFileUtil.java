package com.dualnback.dao;


import android.support.annotation.NonNull;

import com.dualnback.io.FileIO;
import com.dualnback.util.FileUtil;

import java.io.File;

public class DataFileUtil {

    public static DataPointCollection readAllData( File filesDir ) {
        Dao dao = getDao( filesDir );

        return dao.read();
    }

    public static DataPointCollection readAllDataSortedByDate( File filesDir ) {

        return readAllData( filesDir ).sortedDataPoints();
    }

    @NonNull
    private static Dao getDao( File filesDir ) {
        File file = FileUtil.getDataFile( filesDir );

        return new FileBasedDao( new FileIO( file ) );
    }

}
