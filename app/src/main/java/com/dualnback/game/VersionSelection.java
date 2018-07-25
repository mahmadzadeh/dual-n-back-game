package com.dualnback.game;


import com.dualnback.dao.Dao;
import com.dualnback.dao.DataDto;
import com.dualnback.dao.DataPoint;

import java.util.List;

class VersionSelection {

    private final Dao dao;

    VersionSelection( Dao dao ) {
        this.dao = dao;
    }

    public NBackVersion currentLevel( ) {
        DataDto sortedData = dao.read().sortedDataPoints();

        List<DataPoint> scoreData = sortedData.userDataPoints();

        if ( scoreData.size() > 0 ) {

        }

        return NBackVersion.TwoBack;
    }


}
