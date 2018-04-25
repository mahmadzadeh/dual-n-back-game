package com.dualnback.util;

public class NumberFormatterUtil {

    public static String formatScore( double number ) {
        return ( ( int ) Math.round( number ) ) + "%";
    }
}
