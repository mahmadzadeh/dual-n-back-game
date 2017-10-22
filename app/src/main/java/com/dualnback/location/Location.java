package com.dualnback.location;


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

    public boolean matches( Location another ) {
        return this.row == another.row && this.col == another.col;
    }

    @Override
    public String toString( ) {
        return "Location{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
