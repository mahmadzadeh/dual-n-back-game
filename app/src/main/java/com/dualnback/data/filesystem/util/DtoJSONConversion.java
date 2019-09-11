package com.dualnback.data.filesystem.util;


import com.dualnback.data.filesystem.dao.DataPoint;
import com.dualnback.data.filesystem.dao.DataPointCollection;
import com.dualnback.util.DateUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.dualnback.data.filesystem.util.JSONUtil.DATAPOINT_ELEMENT;
import static com.dualnback.data.filesystem.util.JSONUtil.DATA_ELEMENT;
import static com.dualnback.data.filesystem.util.JSONUtil.DATE_ELEMENT;
import static com.dualnback.data.filesystem.util.JSONUtil.SCORE_ELEMENT;
import static com.dualnback.data.filesystem.util.JSONUtil.VERSION_ELEMENT;


public class DtoJSONConversion {

    public static JSONObject dataPointToJSON( DataPoint dataPoint ) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        JSONObject innerObject = new JSONObject();

        innerObject.put( DATE_ELEMENT, DateUtil.format( dataPoint.date() ) );
        innerObject.put( SCORE_ELEMENT, dataPoint.score() );
        innerObject.put( VERSION_ELEMENT, dataPoint.version().getTextRepresentation() );

        jsonObject.put( DATAPOINT_ELEMENT, innerObject );

        return jsonObject;
    }

    public static JSONObject dataDtoToJSON( DataPointCollection dto ) throws JSONException {

        JSONObject rootObject = new JSONObject();
        JSONArray array = new JSONArray();

        for ( DataPoint dataPoint : dto.userDataPoints() ) {
            array.put( dataPointToJSON( dataPoint ) );
        }

        rootObject.put( DATA_ELEMENT, array );

        return rootObject;
    }

}
