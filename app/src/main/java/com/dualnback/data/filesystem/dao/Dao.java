package com.dualnback.data.filesystem.dao;


public interface Dao {

    DataPointCollection read( );

    void write( DataPointCollection data );

}
