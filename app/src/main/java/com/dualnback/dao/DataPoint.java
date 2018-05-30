package com.dualnback.dao;

import org.json.JSONException;

import java.util.Date;

import static com.dualnback.util.DtoJSONConversion.dataPointToJSON;


public class DataPoint implements Comparable<DataPoint> {

    private final Date date;
    private final int score;

    public DataPoint( Date date, int score ) {
        this.date = date;
        this.score = score;
    }

    public Date date( ) {
        return date;
    }

    public int score( ) {
        return score;
    }

    @Override
    public String toString( ) {
        return "data point with date: " + date.toString() + " and score: " + score;
    }

    public String toJSON( ) {
        try {
            return dataPointToJSON( this ).toString();
        } catch ( JSONException e ) {
            return "";
        }
    }

    @Override
    public int compareTo( DataPoint another ) {
        return this.date.after( another.date ) ? 1 : ( this.date.before( another.date ) ? -1 : 0 );
    }
}
