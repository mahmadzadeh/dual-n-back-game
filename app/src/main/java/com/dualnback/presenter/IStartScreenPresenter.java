package com.dualnback.presenter;

import com.dualnback.game.NBackVersion;


interface IStartScreenPresenter {
    NBackVersion getCurrentVersion( int minScoreToUpgrade, int minScoreToMaintain );

    NBackVersion getSelectedVersion( CharSequence dropDownItem );
}
