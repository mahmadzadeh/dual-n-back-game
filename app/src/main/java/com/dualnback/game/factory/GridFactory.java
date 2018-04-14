package com.dualnback.game.factory;

import com.dualnback.R;
import com.dualnback.SwappableImage;
import com.dualnback.game.Cell;
import com.dualnback.game.DualBackGrid;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class GridFactory {

    public static DualBackGrid instance( SwappableImage context ) {
        int onImg = R.mipmap.square;
        int offImg = R.mipmap.square_blue;

        List<List<Cell>> grid =
                range( 0, 3 )
                        .mapToObj( row ->
                                range( 0, 3 )
                                        .mapToObj( col -> createSingleCell( onImg, offImg, row, col, context ) )
                                        .collect( Collectors.toList() ) )
                        .collect( Collectors.toList() );

        return new DualBackGrid( grid );
    }

    private static Cell createSingleCell( int onImg, int offImg, int row, int col, SwappableImage context ) {
        return new Cell( onImg, offImg, context );
    }
}
