package com.dualnback.data.filesystem.io;


public class FileIOException extends Exception {

    public FileIOException( String detailMessage ) {
        super( detailMessage );
    }

    public FileIOException( String detailMessage, Throwable throwable ) {
        super( detailMessage, throwable );
    }
}
