package com.dualnback.ui.mainscreen;

import android.graphics.Color;

import com.dualnback.data.location.Location;
import com.dualnback.game.Cell;
import com.dualnback.game.factory.DualBackGameFactory;
import com.dualnback.game.factory.GameParameters;
import com.dualnback.ui.mainscreen.util.GameCountDownTimer;

import java.util.Optional;

import androidx.annotation.NonNull;

import static com.dualnback.game.LocationToImageMapper.map;
import static com.dualnback.ui.mainscreen.MainActivity.COUNT_DOWN_INTERVAL_IN_MILLIS;
import static com.dualnback.ui.mainscreen.MainActivity.TOTAL_TRIAL_COUNT;
import static com.dualnback.ui.mainscreen.util.TimerUtil.getOneRoundTime;
import static com.dualnback.util.NumberFormatterUtil.formatScore;


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
                getOneRoundTime(
                        parameters.getConfig().singleTrialDurationInMillis(),
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

        Cell cellToTurnOff = getCellToTurnOff( "Unable to mark end of a trial" );

        mainScreenView.setScoreTextRound( formatScore( model.getCurrentScore() ) );

        Location offLocation = getLocationOrException( cellToTurnOff, "Unable to locate cell to turn off " );

        mainScreenView.updateCellState(
                map( offLocation ), cellToTurnOff.getCurrentState()
        );

        startTrial();
    }

    @Override
    public void startTrial( ) {
        Optional<Cell> cellToTurnOn = model.markStartOfTrial();

        model.getCurrentTrial().ifPresent( c -> c.getSound().playSound() );

        if ( cellToTurnOn.isPresent() ) {
            Location onLocation = getLocationOrException( cellToTurnOn.get(), "Unable to locate cell to turn on" );
            mainScreenView.updateCellState( map( onLocation ), cellToTurnOn.get().getCurrentState() );
        }

    }

    @Override
    public void onFinish( ) {
        mainScreenView.onFinish( model.currentPoints() );
    }

    @Override
    public void startTimer( ) {
        timer.start();
    }

    @Override
    public void pauseTimer( ) {
        timer.onPause();
    }

    private boolean isTimeToChangeTimeTxtColour( String now ) {
        return getSecondsLeft( now ) <= TIME_FOR_TEXT_COLOUR_CHANGE;
    }

    @NonNull
    private Integer getSecondsLeft( String time ) {

        String[] split = time.split( ":" );
        return ( Integer.valueOf( split[ 0 ] ) * 60 ) + Integer.valueOf( split[ 1 ] );

    }

    @NonNull
    private Cell getCellToTurnOff( String message ) throws RuntimeException {
        try {
            return model.markEndOfTrial().orElseThrow( ( ) -> new RuntimeException( message ) );
        } catch ( Throwable throwable ) {
            throw new RuntimeException( message );
        }
    }

    @NonNull
    private Location getLocationOrException( Cell cellToTurnOn, String s ) throws RuntimeException {
        try {
            return model.findCellLocation( cellToTurnOn ).orElseThrow(
                    ( ) -> new IllegalStateException( s + cellToTurnOn ) );
        } catch ( Throwable throwable ) {
            throw new RuntimeException( s );
        }
    }

}
