package com.dualnback.dao;


import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

import static com.shapematchandroid.util.DateUtil.formatForChartUI;

public class DataDtoConversion {
    public static ChartData convertToChartData( DataDto dataDto ) {

        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<>();

        for ( DataPoint dataPoint : dataDto.userDataPoints() ) {
            xVals.add( formatForChartUI( dataPoint.date() ) );
            yVals.add( new Entry( dataPoint.score(), xVals.size() - 1 ) );
        }

        return new ChartData( xVals, yVals );
    }
}
