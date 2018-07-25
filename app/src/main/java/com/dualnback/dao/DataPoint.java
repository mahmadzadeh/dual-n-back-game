package com.dualnback.dao;

import com.dualnback.game.NBackVersion;

import org.json.JSONException;

import java.util.Date;

import static com.dualnback.util.DtoJSONConversion.dataPointToJSON;


public class DataPoint implements Comparable<DataPoint> {

    private final Date date;
    private final int score;

    private final NBackVersion version;

    public DataPoint( Date date, int score, NBackVersion version ) {
        this.date = date;
        this.score = score;
        this.version = version;
    }

    public Date date( ) {
        return date;
    }

    public int score( ) {
        return score;
    }

    public NBackVersion getVersion( ) {
        return version;
    }

    @Override
    public String toString( ) {
        return "DataPoint {" +
                "date=" + date +
                ", score=" + score +
                ", version=" + version +
                '}';
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
