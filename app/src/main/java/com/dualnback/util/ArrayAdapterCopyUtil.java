package com.dualnback.util;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapterCopyUtil {

    // Copy the data into a regular list so that ArrayAdapter is not leaked into
    // parts of the code that need to stay clean of android-y- stuff
    public static List<CharSequence> copyToRegularArrayList( ArrayAdapter<CharSequence> adapter ) {

        List<CharSequence> list = new ArrayList<>();

        for ( int i = 0; i < adapter.getCount(); ++i ) {
            list.add( adapter.getItem( i ) );
        }

        return list;
    }
}
