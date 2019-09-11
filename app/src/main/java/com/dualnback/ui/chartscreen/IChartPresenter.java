package com.dualnback.ui.chartscreen;

import com.dualnback.data.filesystem.dao.ChartData;
import com.dualnback.data.filesystem.dao.DataPoint;

interface IChartPresenter {

    void onCreate( );

    void onPause( );

    void onResume( );

    void onDestroy( );

    void saveData( );

    void addDataPoint( DataPoint lastDataPoint );

    ChartData convertToChartData( );
}
