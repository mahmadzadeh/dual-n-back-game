package com.dualnback.util;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DateUtilTest {

    @Test
    public void givenTextDateAndFormatThenParseMethodWillParseIt( ) {

        String date = "2012-04-23T18:25:43.51";
        String format = "yyyy-MM-dd'T'HH:mm:ss";

        assertNotNull( DateUtil.parse( date, format ) );
    }

    @Test
    public void givenTextDateThenParseMethodWillParseIt( ) {

        String date = "2012-04-23T18:25:43.51";

        assertNotNull( DateUtil.parse( date ) );
    }

    @Test
    public void givenDateTextThenParseMethodWillParseIt( ) {

        String date = "2012-04-23T18:25:43.51";

        assertNotNull( DateUtil.parse( date ) );
    }

    @Test
    public void givenDateThenFormatMethodWillFormatIt( ) {

        long milliseconds = 1461040173716l; // Apr 18 2016 - 9:29 PM
        Date date = new Date( milliseconds );

        assertThat( DateUtil.format( date ), is( equalTo( "2016-04-18T21:29:33" ) ) );
    }

}