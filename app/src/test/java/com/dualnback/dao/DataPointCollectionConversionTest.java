package com.dualnback.dao;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class DataPointCollectionConversionTest {

    @Test
    public void givenEmptyDataDtoThenConvertToChartDataReturnsEmptyChartData( ) {

        DataPointCollection dataPointCollection = new DataPointCollection( new ArrayList<DataPoint>() );

        ChartData chartData = DataDtoConversion.convertToChartData( dataPointCollection );

        assertEquals( 0, chartData.getxVals().size() );
        assertEquals( 0, chartData.getyVals().size() );
    }

    @Test
    public void givenDataDtoWithOneDataPointThenConvertToChartDataReturnsChartDataWithOneXAndYValue( ) {

        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add( new DataPoint( new Date( 1461478244180l ), 14, null ) );

        DataPointCollection dataPointCollection = new DataPointCollection( dataPoints );

        ChartData chartData = DataDtoConversion.convertToChartData( dataPointCollection );

        assertEquals( "2016/04/23-23:10:44", chartData.getxVals().get( 0 ) );
        assertEquals( 14, chartData.getyVals().get( 0 ).getVal(), 0.1 );
    }

}