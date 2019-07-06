package com.dualnback.presenter;

import android.graphics.Color;
import android.support.annotation.NonNull;

import com.dualnback.game.Cell;
import com.dualnback.game.factory.DualBackGameFactory;
import com.dualnback.game.factory.GameParameters;
import com.dualnback.location.Location;
import com.dualnback.model.MainActivityModel;
import com.dualnback.util.timer.GameCountDownTimer;
import com.dualnback.view.MainScreenView;

import static com.dualnback.MainActivity.COUNT_DOWN_INTERVAL_IN_MILLIS;
import static com.dualnback.MainActivity.TOTAL_TRIAL_COUNT;
import static com.dualnback.game.LocationToImageMapper.map;
import static com.dualnback.util.NumberFormatterUtil.formatScore;
import static com.dualnback.util.timer.TimerUtil.getOneRoundTime;


public class MainActivityPresenter implements MainViewContract.Presenter {

    public static final int TIME_FOR_TEXT_COLOUR_CHANGE = 10;
    public static final int COUNT_DOWN_WARNING_COLOUR = Color.YELLOW;
    public static final int TIME_TEXT_NORMAL_COLOUR = Color.WHITE;

    private final MainScreenView mainScreenView;
    private final MainActivityModel model;
    private final GameParameters parameters;
    private final GameCountDownTimer timer;

    public MainActivityPresenter( MainScreenView mainScreenView, GameParameters parameters ) {
        this.mainScreenView = mainScreenView;
        this.model = new MainActivityModel( DualBackGameFactory.create( parameters ) );

        this.timer = GameCountDownTimer.INSTANCE( this,
                getOneRoundTime( parameters.getConfig().singleTrialDurationInMillis(),
                        TOTAL_TRIAL_COUNT ),
                COUNT_DOWN_INTERVAL_IN_MILLIS,
                parameters.getConfig().singleTrialDurationInMillis() );

        this.parameters = parameters;
    }

    @Override
    public void handleLocationButtonClick( ) {
        boolean isCorrectAnswer = model.recordLocationMatch();

        mainScreenView.vibrateFor( parameters.getConfig().vibrationLength() );

        mainScreenView.setPositionMatchFeedBack( isCorrectAnswer );

        mainScreenView.updateLocationFeedBackImage();
    }

    @Override
    public void handleSoundButtonClick( ) {
        boolean isCorrectAnswer = model.recordSoundMatch();

        mainScreenView.vibrateFor( parameters.getConfig().vibrationLength() );

        mainScreenView.setSoundMatchFeedBack( isCorrectAnswer );

        mainScreenView.updateSoundFeedBackImage();
    }

    @Override
    public void setCountDownText( String text ) {

        if ( isTimeToChangeTimeTxtColour( text ) ) {
            mainScreenView.setCountDownTextAndColor( text, COUNT_DOWN_WARNING_COLOUR );
        } else {
            mainScreenView.setCountDownText( text );
        }
    }

    @Override
    public void endTrial( ) {

        Cell cellToTurnOff = model.markEndOfTrial().orElseThrow(
                ( ) -> new IllegalStateException( "Unable to mark end of a trial" ) );

        mainScreenView.setScoreTextRound( formatScore( model.getCurrentScore() ) );

        Location offLocation = model.findCellLocation( cellToTurnOff )
                .orElseThrow( ( ) -> new IllegalStateException( "Unable to locate cell to turn off " + cellToTurnOff ) );

        mainScreenView.updateCellState(
                map( offLocation ), cellToTurnOff.getCurrentState()
        );

        startTrial();
    }

    @Override
    public void startTrial( ) {
        Cell cellToTurnOn = model.markStartOfTrial();

        model.getCurrentTrial().getSound().playSound();

        Location onLocation = model.findCellLocation( cellToTurnOn ).orElseThrow(
                ( ) -> new IllegalStateException( "Unable to locate cell to turn on" + cellToTurnOn ) );

        mainScreenView.updateCellState( map( onLocation ), cellToTurnOn.getCurrentState() );
    }

    @Override
    public void onFinish( ) {
        mainScreenView.onFinish( model.currentPoints() );
    }

    @Override
    public void startTimer( ) {
        timer.start();
    }

    private boolean isTimeToChangeTimeTxtColour( String now ) {
        return getSecondsLeft( now ) <= TIME_FOR_TEXT_COLOUR_CHANGE;
    }

    @NonNull
    private Integer getSecondsLeft( String time ) {

        String[] split = time.split( ":" );
        return ( Integer.valueOf( split[ 0 ] ) * 60 ) + Integer.valueOf( split[ 1 ] );

    }

}
