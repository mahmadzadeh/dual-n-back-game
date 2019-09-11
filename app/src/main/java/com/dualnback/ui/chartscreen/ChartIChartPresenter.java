package com.dualnback.ui.chartscreen;

import com.dualnback.data.filesystem.dao.ChartData;
import com.dualnback.data.filesystem.dao.DataPoint;

public class ChartIChartPresenter implements IChartPresenter {
    private final ChartModel model;
    private final ChartView view;

    public ChartIChartPresenter( ChartView chartView ) {
        this.view = chartView;
        this.model = new ChartModel( view.getFilesDirectory() );
    }

    @Override
    public void addDataPoint( DataPoint dataPoint ) {
        this.model.addDataPoint( dataPoint );
    }

    @Override
    public ChartData convertToChartData( ) {
        return model.chartData();
    }

    @Override
    public void onCreate( ) {

    }

    @Override
    public void onPause( ) {

    }

    @Override
    public void onResume( ) {

    }

    @Override
    public void onDestroy( ) {

    }

    @Override
    public void saveData( ) {
        model.saveData();
    }
}
