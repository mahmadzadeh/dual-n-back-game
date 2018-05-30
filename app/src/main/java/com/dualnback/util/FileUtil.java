package com.dualnback.util;

import android.content.Context;

import java.io.File;
import java.io.IOException;


public class FileUtil {

    public static final String FILE_NAME = "data.json";

    public static File getDataFile( Context context, String fileName ) {
        File file = new File( context.getFilesDir(), fileName );

        if ( !file.exists() ) {
            try {
                file.createNewFile();
            } catch ( IOException e ) {
                throw new RuntimeException( "Unable create file " + file.getAbsolutePath() );
            }
        }

        return file;

    }

    public static File getDataFile( Context context ) {
        return getDataFile( context, FILE_NAME );

    }
}
