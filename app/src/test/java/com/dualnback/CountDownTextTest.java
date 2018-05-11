package com.dualnback;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.dualnback.CountDownText.COUNT_DOWN_WARNING_COLOUR;
import static com.dualnback.CountDownText.TIME_TEXT_NORMAL_COLOUR;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class CountDownTextTest {

    @Mock
    TextView mockTextView;


    @Test
    public void canCreateInstance( ) {
        new CountDownText( mockTextView );
    }

    @Test
    public void givenNotInLastTenSecondsThenSetTextDisplaysTextInNormalColour( ) throws Exception {

        String time = "00:15";

        CountDownText countDownText = new CountDownText( mockTextView );

        setUpMockTextView( time, TIME_TEXT_NORMAL_COLOUR );

        countDownText.setText( time );

        verifyMockInteractions( time, TIME_TEXT_NORMAL_COLOUR );
    }

    @Test
    public void givenTwoMinutesLectThenSetTextDisplaysTextInNormalColour( ) throws Exception {

        String time = "01:09";

        CountDownText countDownText = new CountDownText( mockTextView );

        setUpMockTextView( time, TIME_TEXT_NORMAL_COLOUR );

        countDownText.setText( time );

        verifyMockInteractions( time, TIME_TEXT_NORMAL_COLOUR );
    }

    @Test
    public void givenTenSecondsToGameEndThenTimeIsDisplayedInRed( ) {
        String time = "00:10";

        CountDownText countDownText = new CountDownText( mockTextView );

        setUpMockTextView( time, COUNT_DOWN_WARNING_COLOUR );

        countDownText.setText( time );

        verifyMockInteractions( time, COUNT_DOWN_WARNING_COLOUR );
    }

    @Test
    public void givenEightSecondsLeftThenTimeIsDisplaedInRed( ) {
        String time = "00:08";

        CountDownText countDownText = new CountDownText( mockTextView );

        setUpMockTextView( time, COUNT_DOWN_WARNING_COLOUR );

        countDownText.setText( time );

        verifyMockInteractions( time, COUNT_DOWN_WARNING_COLOUR );
    }

    @Test
    public void givenZeroSecondLeftTheTimeIsStillDisplayedInRed( ) {
        String time = "00:00";

        CountDownText countDownText = new CountDownText( mockTextView );

        setUpMockTextView( time, COUNT_DOWN_WARNING_COLOUR );

        countDownText.setText( time );

        verifyMockInteractions( time, COUNT_DOWN_WARNING_COLOUR );
    }

    private void setUpMockTextView( String time, int colour ) {
        doNothing().when( mockTextView ).setText( time );
        doNothing().when( mockTextView ).setTextColor( colour );
    }

    private void verifyMockInteractions( String time, int colour ) {
        verify( mockTextView ).setText( time );
        verify( mockTextView ).setTextColor( colour );
    }


}