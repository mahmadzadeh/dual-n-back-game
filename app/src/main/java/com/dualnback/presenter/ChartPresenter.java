package com.dualnback.presenter;

import com.dualnback.dao.ChartData;
import com.dualnback.dao.DataPoint;
import com.dualnback.model.ChartModel;
import com.dualnback.view.ChartView;

public class ChartPresenter implements Presenter {
    private final ChartModel model;
    private final ChartView view;

    public ChartPresenter( ChartView chartView ) {
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
