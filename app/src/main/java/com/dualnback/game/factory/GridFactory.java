package com.dualnback.game.factory;

import com.dualnback.R;
import com.dualnback.game.AlternativeDualBackGrid;
import com.dualnback.game.Cell;

import java.util.List;

import static java.util.Arrays.asList;

public class GridFactory {

    public static AlternativeDualBackGrid instance( ) {
        int onImage = R.mipmap.square;
        int offImage = R.mipmap.square_blue;

        List<Cell> row1 = asList(
                new Cell( onImage, offImage ),
                new Cell( onImage, offImage ),
                new Cell( onImage, offImage ) );

        List<Cell> row2 = asList(
                new Cell( onImage, offImage ),
                new Cell( onImage, offImage ),
                new Cell( onImage, offImage ) );

        List<Cell> row3 = asList(
                new Cell( onImage, offImage ),
                new Cell( onImage, offImage ),
                new Cell( onImage, offImage ) );

        return new AlternativeDualBackGrid( asList( row1, row2, row3 ) );
    }
}
