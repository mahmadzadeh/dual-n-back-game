package com.dualnback.data.location;

public class Location {

    private final int col;
    private final int row;

    public Location( int row, int col ) {
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
        return this.equals( another );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Location location = ( Location ) o;

        if ( getCol() != location.getCol() ) return false;
        return getRow() == location.getRow();
    }

    @Override
    public int hashCode( ) {
        int result = getCol();
        result = 31 * result + getRow();
        return result;
    }

    @Override
    public String toString( ) {
        return "Location{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
