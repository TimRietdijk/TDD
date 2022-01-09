package nl.hanze.hive;

import java.util.ArrayList;

public interface Piece {

    BoardClass board = BoardClass.getInstance();

    Hive.Player getColour();

    void setColour(Hive.Player colour) ;

    Hive.Tile getImage();

    ArrayList<HexagonClass> getValidMoves(HexagonClass hexagon);

}
