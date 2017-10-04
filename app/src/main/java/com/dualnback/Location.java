package com.dualnback;

public class Location {

    private final int col;
    private final int row;

    public Location( int col, int row ) {
        this.col = col;
        this.row = row;
    }

    public int getCol( ) {
        return col;
    }

    public int getRow( ) {
        return row;
    }

}
