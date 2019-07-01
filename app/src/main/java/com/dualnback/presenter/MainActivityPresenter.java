package com.dualnback.presenter;

import com.dualnback.game.factory.DualBackGameFactory;
import com.dualnback.game.factory.GameParameters;
import com.dualnback.model.MainActivityModel;
import com.dualnback.view.MainScreenView;


public class MainActivityPresenter {

    private final MainScreenView mainScreenView;
    private final MainActivityModel model;

    public MainActivityPresenter( MainScreenView mainScreenView, GameParameters parameters ) {
        this.mainScreenView = mainScreenView;
        this.model = new MainActivityModel( DualBackGameFactory.create( parameters ) );
    }

    public MainActivityPresenter( MainScreenView mainScreenView, MainActivityModel model ) {
        this.mainScreenView = mainScreenView;
        this.model = model;
    }
}
