package com.dualnback.presenter;

import com.dualnback.dao.ChartData;
import com.dualnback.dao.DataPoint;

interface Presenter {

    void onCreate( );

    void onPause( );

    void onResume( );

    void onDestroy( );

    void saveData( );

    void addDataPoint( DataPoint lastDataPoint );

    ChartData convertToChartData( );
}