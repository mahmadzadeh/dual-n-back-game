package com.dualnback.util.timer;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.dualnback.ui.mainscreen.util.TimerUtil.formatTime;
import static com.dualnback.ui.mainscreen.util.TimerUtil.getOneRoundTime;
import static com.dualnback.ui.mainscreen.util.TimerUtil.isEndOfTrialYet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


public class TimerUtilTest {


    @Test
    public void isEndOfRoundYetReturnsTrueForAllTimesDivisibleBySingleTick( ) throws Exception {
        int oneGameInMillis = 75000;
        int singleTick = 3000;

        generateList( oneGameInMillis, i -> ( i % singleTick ) == 0 )
                .stream()
                .forEach( it -> {
                    assertTrue( isEndOfTrialYet( it, singleTick ) );
                } );

    }

    @Test
    public void testIsEndOfTrialYetWithNonRoundNumbers( ) {

        assertThat( isEndOfTrialYet( 69555l, 2000 ) ).isFalse();
        assertThat( isEndOfTrialYet( 3001l, 3000 ) ).isTrue();
        assertThat( isEndOfTrialYet( 6547, 6000 ) ).isTrue();
        assertThat( isEndOfTrialYet( 6547, 5000 ) ).isFalse();
    }

    @Test
    public void testTimeFormatting( ) {
        // map of: format time in string form -> current time in millis
        Map<String, Long> testMap = new HashMap<String, Long>() {{
            put( "00:00", 0l );
            put( "00:01", 1000l );
            put( "00:02", 2000l );
            put( "00:03", 3000l );
            put( "00:09", 9000l );
            put( "00:10", 10000l );
            put( "00:25", 25000l );
            put( "00:25", 25999l );
            put( "01:00", 60000l );
            put( "01:00", 60001l );
            put( "01:01", 61000l );
            put( "01:14", 74999l );
            put( "01:15", 75000l );
        }};

        testMap
                .keySet()
                .stream()
                .forEach( val -> {
                    assertThat( formatTime( testMap.get( val ) ) ).isEqualTo( val );
                } );
    }

    @Test
    public void givenSingleTrialInMillisAndTotalTrialCntThenGetOnRoundTimeReturnsTimeForOneRound( ) {

        long singleTrial = 3000l;
        int totalTrialCount = 24;

        assertThat( getOneRoundTime( singleTrial, totalTrialCount ) ).isEqualTo( 72000 );
    }


    private List<Long> generateList( int endVal, IntPredicate predicate ) {

        return IntStream
                .range( 1000, endVal )
                .filter( predicate )
                .mapToObj( val -> new Long( val ) )
                .collect( Collectors.toList() );
    }

}