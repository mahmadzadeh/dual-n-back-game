package com.dualnback.ui.chartscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.dualnback.R;
import com.dualnback.data.filesystem.dao.ChartData;
import com.dualnback.data.filesystem.dao.DataPoint;
import com.dualnback.ui.startscreen.StartScreenActivityIntentUtil;
import com.dualnback.util.IntentUtility;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.File;
import java.util.ArrayList;

import static com.dualnback.ui.chartscreen.ChartUtil.dataSetForYAxis;
import static com.dualnback.ui.chartscreen.ChartUtil.setUpChart;


public class ChartActivity extends AppCompatActivity implements ChartView {

    private ChartIChartPresenter presenter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        presenter = new ChartIChartPresenter( this );

        setContentView( R.layout.chart_screen );

        LineChart lineChart = findViewById( R.id.line_chart );

        DataPoint lastDataPoint = IntentUtility.extractDatePointFromExtras( getIntent().getExtras() );

        presenter.addDataPoint( lastDataPoint );

        setData( lineChart );

        Button continueButton = findViewById( R.id.chart_continue );

        continueButton.setOnClickListener( v -> {
            presenter.saveData();
            StartScreenActivityIntentUtil.backToStartScreen( v, ChartActivity.this );
        } );
    }

    private void setData( LineChart mChart ) {

        ChartData chartData = presenter.convertToChartData();

        LineDataSet dataSet = dataSetForYAxis( chartData.getyVals() );

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add( dataSet );

        setUpChart( mChart, new LineData( chartData.getxVals(), dataSets ) );
    }

    @Override
    public File getFilesDirectory( ) {
        return this.getFilesDir();
    }
}
