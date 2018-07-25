package com.dualnback.util;


import com.dualnback.dao.DataDto;
import com.dualnback.dao.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.dualnback.util.JSONUtil.DATAPOINT_ELEMENT;
import static com.dualnback.util.JSONUtil.DATA_ELEMENT;
import static com.dualnback.util.JSONUtil.DATE_ELEMENT;
import static com.dualnback.util.JSONUtil.SCORE_ELEMENT;
import static com.dualnback.util.JSONUtil.VERSION_ELEMENT;


public class DtoJSONConversion {

    public static JSONObject dataPointToJSON( DataPoint dataPoint ) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        JSONObject innerObject = new JSONObject();

        innerObject.put( DATE_ELEMENT, DateUtil.format( dataPoint.date() ) );
        innerObject.put( SCORE_ELEMENT, dataPoint.score() );
        innerObject.put( VERSION_ELEMENT, dataPoint.getVersion().getTextRepresentation() );

        jsonObject.put( DATAPOINT_ELEMENT, innerObject );

        return jsonObject;
    }

    public static JSONObject dataDtoToJSON( DataDto dto ) throws JSONException {

        JSONObject rootObject = new JSONObject();
        JSONArray array = new JSONArray();

        for ( DataPoint dataPoint : dto.userDataPoints() ) {
            array.put( dataPointToJSON( dataPoint ) );
        }

        rootObject.put( DATA_ELEMENT, array );

        return rootObject;
    }

}
