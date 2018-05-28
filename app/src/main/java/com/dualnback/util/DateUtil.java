package com.dualnback.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_PATTERN_FOR_CHART_DISPLAY = "yyyy/MM/dd-HH:mm:ss";

    public static Date parse( String date, String format ) {
        return new SimpleDateFormat( format )
                .parse( date, new ParsePosition( 0 ) );
    }

    public static Date parse( String date ) {
        return new SimpleDateFormat( FORMAT_PATTERN )
                .parse( date, new ParsePosition( 0 ) );
    }

    public static String format( Date date ) {
        return new SimpleDateFormat( FORMAT_PATTERN ).format( date );
    }

    public static String formatForChartUI( Date date ) {
        return new SimpleDateFormat( FORMAT_PATTERN_FOR_CHART_DISPLAY ).format( date );
    }
}
