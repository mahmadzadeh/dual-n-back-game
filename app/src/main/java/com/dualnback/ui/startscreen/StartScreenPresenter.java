package com.dualnback.ui.startscreen;

import com.dualnback.data.filesystem.dao.DataPoint;
import com.dualnback.game.NBackVersion;

import java.util.Optional;

import static com.dualnback.data.filesystem.dao.DataFileUtil.readAllData;
import static com.dualnback.game.VersionSelection.currentLevel;

public class StartScreenPresenter implements IStartScreenPresenter {

    private static final NBackVersion DEFAULT_VERSION = NBackVersion.TwoBack;

    private final StartScreenView startScreenView;

    public StartScreenPresenter( StartScreenView startScreenView ) {
        this.startScreenView = startScreenView;
    }

    @Override
    public NBackVersion getCurrentVersion( int minScoreToUpgrade, int minScoreToMaintain ) {
        Optional<DataPoint> lastDataPoint = readAllData( startScreenView.getFilesDir() ).getLastDataPoint();

        return currentLevel( lastDataPoint, minScoreToUpgrade, minScoreToMaintain );
    }

    @Override
    public NBackVersion getSelectedVersion( CharSequence dropDownItem ) {
        return NBackVersion.fromTextValue( dropDownItem.toString() ).orElse( DEFAULT_VERSION );
    }
}
