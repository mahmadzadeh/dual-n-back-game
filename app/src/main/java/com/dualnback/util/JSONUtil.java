package com.dualnback.util;


import com.dualnback.dao.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONUtil {

    public static final String DATA_ELEMENT = "data";
    public static final String DATAPOINT_ELEMENT = "datapoint";
    public static final String DATE_ELEMENT = "date";
    public static final String SCORE_ELEMENT = "score";

    public static List<DataPoint> parse( String jsonString ) throws JSONException {

        JSONObject jsonObject = new JSONObject( jsonString );
        JSONArray array = jsonObject.optJSONArray( DATA_ELEMENT );

        List<DataPoint> dataPoints = new ArrayList<>();

        for ( int i = 0; i < array.length(); i++ ) {
            JSONObject oneRow = ( JSONObject ) array.get( i );
            JSONObject innerDataPointElem = oneRow.getJSONObject( DATAPOINT_ELEMENT );

            Date date = DateUtil.parse( innerDataPointElem.getString( DATE_ELEMENT ) );
            int score = innerDataPointElem.getInt( SCORE_ELEMENT );

            dataPoints.add( new DataPoint( date, score ) );
        }

        return dataPoints;
    }
}
