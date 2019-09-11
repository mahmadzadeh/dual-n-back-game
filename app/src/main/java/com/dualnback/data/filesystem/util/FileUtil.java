package com.dualnback.data.filesystem.util;

import java.io.File;
import java.io.IOException;


public class FileUtil {

    public static final String FILE_NAME = "data.json";

    public static File getDataFile( File filesDir, String fileName ) {
        File file = new File( filesDir, fileName );

        if ( !file.exists() ) {
            try {
                file.createNewFile();
            } catch ( IOException e ) {
                throw new RuntimeException( "Unable create file " + file.getAbsolutePath() );
            }
        }

        return file;

    }

    public static File getDataFile( File filesDir ) {
        return getDataFile( filesDir, FILE_NAME );

    }
}
