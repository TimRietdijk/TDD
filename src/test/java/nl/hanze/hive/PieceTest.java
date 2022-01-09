package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {

    @Test
    void getColour() {
        // ARRANGE
        Piece piece = new QueenBeeClass();
        piece.setColour(Hive.Player.WHITE);
        // ACT
        Hive.Player colour = piece.getColour();
        // ASSERT
        assertEquals(colour, Hive.Player.WHITE);
    }

    @Test
    void setColour() {
        // ARRANGE
        Piece piece = new QueenBeeClass();
        // ACT
        piece.setColour(Hive.Player.WHITE);
        // ASSERT
        assertEquals(piece.getColour(), Hive.Player.WHITE);
    }

    @Test
    void getImage() {
        // ARRANGE
        Piece piece = new QueenBeeClass();
        // ACT
        Hive.Tile image = piece.getImage();
        // ASSERT
        assertEquals(image, Hive.Tile.QUEEN_BEE);
    }

    @Test
    void getValidMoves() {
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        Piece piece = new QueenBeeClass();
        Piece piece2 = new QueenBeeClass();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass hexagon2 = new HexagonClass(1,0);
        hexagon.addTileToHexagon(piece);
        hexagon2.addTileToHexagon(piece2);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(hexagon2);
        ArrayList<HexagonClass> expectedArray = new ArrayList<>();
        expectedArray.add(board.getHexagonByPosition(1,-1));
        expectedArray.add(board.getHexagonByPosition(0,1));
        // ACT
        ArrayList<HexagonClass> validMoves = piece.getValidMoves(hexagon);
        // ASSERT
        assertEquals(expectedArray, validMoves);
    }
}
