package com.dualnback.dao;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class ChartData {

    private List<String> xVals;
    private List<Entry> yVals;

    public ChartData( List<String> xVals, List<Entry> yVals ) {
        this.xVals = xVals;
        this.yVals = yVals;
    }

    public List<String> getxVals( ) {
        return new ArrayList<>( xVals );
    }

    public List<Entry> getyVals( ) {
        return new ArrayList<>( yVals );
    }


}
