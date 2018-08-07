package com.dualnback.util;

public class NumberFormatterUtil {

    public static String formatScore( double number ) {
        return ( ( int ) Math.round( number ) ) + "%";
    }

    public static int convertToIntOrDefault( String value, int defaultValue ) {
        try {
            return Integer.valueOf( value );
        } catch ( NumberFormatException nfe ) {
            return defaultValue;
        }
    }
}
