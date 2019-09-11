package com.dualnback.data.filesystem.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIO implements IO {

    private File file;

    public FileIO( File file ) {
        if ( file == null || !file.exists() ) {
            throw new IllegalArgumentException( "Invalid file " + file );
        }

        this.file = file;
    }

    @Override
    public String read( ) throws FileIOException {
        int length = ( int ) file.length();

        byte[] bytes = new byte[ length ];
        FileInputStream in = null;

        try {

            in = getFileInputStream();

            in.read( bytes );

        } catch ( FileNotFoundException e ) {

            throw new FileIOException( "Unable to read file " + file, e );

        } catch ( IOException e ) {

            throw new FileIOException( "Unable to read file " + file, e );

        } finally {

            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException e ) {
                    throw new FileIOException( "Unable to close file " + file, e );
                }
            }
        }

        return new String( bytes );
    }

    @Override
    public void write( String data ) throws FileIOException {
        FileOutputStream stream = null;

        try {

            stream = new FileOutputStream( file );

            stream.write( data.getBytes() );

        } catch ( FileNotFoundException e ) {

            throw new FileIOException( "Unable to write to file " + file, e );

        } catch ( IOException e ) {

            throw new FileIOException( "Unable to write to file " + file, e );

        } finally {

            try {
                stream.close();
            } catch ( IOException e ) {
                throw new FileIOException( "Unable to close (after writing) file " + file, e );
            }
        }
    }

    FileInputStream getFileInputStream( ) throws FileNotFoundException {
        return new FileInputStream( file );
    }
}
