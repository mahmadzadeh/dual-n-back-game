package com.dualnback.presenter;

import com.dualnback.dao.DataPoint;
import com.dualnback.game.NBackVersion;
import com.dualnback.view.StartScreenView;

import java.util.Optional;

import static com.dualnback.dao.DataFileUtil.readAllData;
import static com.dualnback.game.VersionSelection.currentLevel;

public class StartScreenPresenter {

    private static final NBackVersion DEFAULT_VERSION = NBackVersion.TwoBack;

    private final StartScreenView startScreenView;

    public StartScreenPresenter( StartScreenView startScreenView ) {
        this.startScreenView = startScreenView;
    }

    public NBackVersion getCurrentVersion( int minScoreToUpgrade, int minScoreToMaintain ) {
        Optional<DataPoint> lastDataPoint = readAllData( startScreenView.getFilesDir() ).getLastDataPoint();

        return currentLevel( lastDataPoint, minScoreToUpgrade, minScoreToMaintain );
    }

    public NBackVersion getSelectedVersion( CharSequence dropDownItem ) {
        return NBackVersion.fromTextValue( dropDownItem.toString() ).orElse( DEFAULT_VERSION );
    }
}
