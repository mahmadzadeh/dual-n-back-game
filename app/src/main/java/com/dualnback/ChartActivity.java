package com.dualnback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.dualnback.dao.ChartData;
import com.dualnback.dao.Dao;
import com.dualnback.dao.DataDto;
import com.dualnback.dao.DataFileUtil;
import com.dualnback.dao.DataPoint;
import com.dualnback.dao.FileBasedDao;
import com.dualnback.io.FileIO;
import com.dualnback.util.FileUtil;
import com.dualnback.util.IntentUtility;
import com.dualnback.util.StartScreenActivityIntentUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.File;
import java.util.ArrayList;

import static com.dualnback.dao.DataDtoConversion.convertToChartData;
import static com.dualnback.util.ChartUtil.dataSetForYAxis;
import static com.dualnback.util.ChartUtil.setUpChart;


public class ChartActivity extends AppCompatActivity {

    private Dao dao;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        File file = FileUtil.getDataFile( this.getFilesDir() );

        dao = new FileBasedDao( new FileIO( file ) );

        setContentView( R.layout.chart_screen );

        LineChart lineChart = findViewById( R.id.line_chart );

        DataPoint lastDataPoint = IntentUtility.extractDatePointFromExtras( getIntent().getExtras() );

        final DataDto allDataSoFar = DataFileUtil
                .readAllDataSortedByDate( this.getFilesDir() )
                .addDataPoint( lastDataPoint );

        setData( lineChart, allDataSoFar );

        Button continueButton = findViewById( R.id.chart_continue );

        continueButton.setOnClickListener( v -> {
            dao.write( allDataSoFar.shrinkDataSize() );
            StartScreenActivityIntentUtil.backToStartScreen( v, ChartActivity.this );
        } );
    }

    private void setData( LineChart mChart, DataDto dto ) {

        ChartData chartData = convertToChartData( dto );

        LineDataSet dataSet = dataSetForYAxis( chartData.getyVals() );

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add( dataSet );

        setUpChart( mChart, new LineData( chartData.getxVals(), dataSets ) );
    }

}
