package com.dualnback.data.filesystem.util;


import com.dualnback.data.filesystem.dao.DataPoint;
import com.dualnback.game.NBackVersion;
import com.dualnback.util.DateUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.dualnback.game.NBackVersion.fromTextValue;

public class JSONUtil {

    public static final String DATA_ELEMENT = "data";
    public static final String DATAPOINT_ELEMENT = "datapoint";
    public static final String DATE_ELEMENT = "date";
    public static final String SCORE_ELEMENT = "score";
    public static final String VERSION_ELEMENT = "version";

    public static final NBackVersion DEFAULT_N_BACK_VERSION = NBackVersion.TwoBack;

    public static List<DataPoint> parse( String jsonString ) throws JSONException {

        JSONObject jsonObject = new JSONObject( jsonString );
        JSONArray array = jsonObject.optJSONArray( DATA_ELEMENT );

        List<DataPoint> dataPoints = new ArrayList<>();

        for ( int i = 0; i < array.length(); i++ ) {
            JSONObject oneRow = ( JSONObject ) array.get( i );
            JSONObject innerDataPointElem = oneRow.getJSONObject( DATAPOINT_ELEMENT );

            Date date = DateUtil.parse( innerDataPointElem.getString( DATE_ELEMENT ) );
            int score = innerDataPointElem.getInt( SCORE_ELEMENT );
            NBackVersion version = fromTextValue( innerDataPointElem.getString( VERSION_ELEMENT ) )
                    .orElse( DEFAULT_N_BACK_VERSION );

            dataPoints.add( new DataPoint( date, score, version ) );
        }

        return dataPoints;
    }
}
