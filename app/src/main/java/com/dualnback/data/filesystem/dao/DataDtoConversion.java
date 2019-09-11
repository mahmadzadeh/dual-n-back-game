package com.dualnback.data.filesystem.dao;


import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

import static com.dualnback.util.DateUtil.formatForChartUI;


public class DataDtoConversion {
    public static ChartData convertToChartData( DataPointCollection dataPointCollection ) {

        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<>();

        for ( DataPoint dataPoint : dataPointCollection.userDataPoints() ) {
            xVals.add( formatForChartUI( dataPoint.date() ) );
            yVals.add( new Entry( dataPoint.score(), xVals.size() - 1 ) );
        }

        return new ChartData( xVals, yVals );
    }
}
