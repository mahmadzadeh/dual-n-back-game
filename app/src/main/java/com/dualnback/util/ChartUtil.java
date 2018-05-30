package com.dualnback.util;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.List;

public class ChartUtil {
    public static final int GREEN = Color.rgb( 123, 220, 76 );

    public static LineDataSet dataSetForYAxis( List<Entry> entries ) {
        LineDataSet dataSet = new LineDataSet( entries, "" );

        dataSet.enableDashedLine( 10f, 5f, 0f );
        dataSet.enableDashedHighlightLine( 10f, 5f, 0f );
        dataSet.setColor( GREEN );
        dataSet.setCircleColor( GREEN );
        dataSet.setLineWidth( 1f );
        dataSet.setCircleRadius( 3f );
        dataSet.setDrawCircleHole( false );
        dataSet.setValueTextSize( 9f );
        dataSet.setDrawFilled( true );
        dataSet.setFillColor( GREEN );
        dataSet.setValueTextColor( Color.WHITE );

        return dataSet;
    }

    public static final String SCORE_DATA_DESC = "Score Data";

    public static void setUpChart( LineChart mChart, LineData data ) {
        mChart.setData( data );
        mChart.setDescription( SCORE_DATA_DESC );
        mChart.setDescriptionColor( Color.WHITE );
        mChart.getAxisLeft().setTextColor( Color.WHITE );
        mChart.getAxisRight().setTextColor( Color.WHITE );
        mChart.getXAxis().setTextColor( Color.WHITE );
    }


}
