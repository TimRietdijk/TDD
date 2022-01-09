package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    @Test
    void soldierAntNormalMove() {
        HiveClass hive = new HiveClass();
        assertDoesNotThrow(()->hive.play(Hive.Tile.QUEEN_BEE, 0,0)); //white
        assertDoesNotThrow(()->hive.play(Hive.Tile.QUEEN_BEE, 1,-1)); //black
        assertThrows(Hive.IllegalMove.class, () ->hive.play(Hive.Tile.BEETLE, 0,-2)); //white
        assertDoesNotThrow(()->hive.play(Hive.Tile.BEETLE, -1,0)); //white
        assertThrows(Hive.IllegalMove.class, () ->hive.play(Hive.Tile.BEETLE, 0,-1)); //black
        assertDoesNotThrow(()->hive.play(Hive.Tile.BEETLE, 2,-2)); //black
        assertThrows(Hive.IllegalMove.class, () ->hive.move(-1,0, -1,-1)); //white
        assertDoesNotThrow(()->hive.move(-1,0, 0,-1)); //white
        assertDoesNotThrow(()->hive.play(Hive.Tile.SOLDIER_ANT, 2,-1)); //black
        assertDoesNotThrow(()->hive.play(Hive.Tile.GRASSHOPPER, -1,1)); //white
        assertThrows(Hive.IllegalMove.class, () ->hive.move(2,-1, 0,0)); //black
        assertDoesNotThrow(()->hive.move(2,-1, -1,-1)); //black
        assertThrows(Hive.IllegalMove.class, () ->hive.move(-1,1, 3,-2)); //white
        assertThrows(Hive.IllegalMove.class, () ->hive.move(-1,1, 0,1)); //white
        assertDoesNotThrow(()->hive.move(-1,1, 3,-3)); //white
        assertThrows(Hive.IllegalMove.class, () ->hive.move(0,-1, -1,0)); //black
        assertDoesNotThrow(()->hive.play(Hive.Tile.SPIDER, 2,-1)); //black
        assertThrows(Hive.IllegalMove.class, () ->hive.pass()); //black
        assertDoesNotThrow(()->hive.move(0,0, 1,0)); //white
        assertDoesNotThrow(()->hive.play(Hive.Tile.SOLDIER_ANT, 3,-1)); //black
        assertDoesNotThrow(()->hive.play(Hive.Tile.SOLDIER_ANT, 1,1)); //white
    }



    @Test
    void test2(){
        HiveClass hive = new HiveClass();
        BoardClass board = BoardClass.getInstance();
        PieceFactory factory = new PieceFactory();
        Piece qb = factory.makePiece(Hive.Tile.QUEEN_BEE);
        Piece beetle1 = factory.makePiece(Hive.Tile.BEETLE);
        Piece beetle2 = factory.makePiece(Hive.Tile.BEETLE);
        Piece beetle3 = factory.makePiece(Hive.Tile.BEETLE);
        Piece beetle4 = factory.makePiece(Hive.Tile.BEETLE);
        Piece beetle5 = factory.makePiece(Hive.Tile.BEETLE);
        Piece beetle6 = factory.makePiece(Hive.Tile.BEETLE);
        qb.setColour(Hive.Player.WHITE);
        beetle1.setColour(Hive.Player.BLACK);
        beetle2.setColour(Hive.Player.BLACK);
        beetle3.setColour(Hive.Player.BLACK);
        beetle4.setColour(Hive.Player.BLACK);
        beetle5.setColour(Hive.Player.BLACK);
        beetle6.setColour(Hive.Player.BLACK);
        HexagonClass hex00 = new HexagonClass(-1,0);
        HexagonClass hex01n1 = new HexagonClass(1,-1);
        HexagonClass hex10 = new HexagonClass(1,0);
        HexagonClass hex01 = new HexagonClass(0,1);
        HexagonClass hexn11 = new HexagonClass(-1,1);
        hex00.addTileToHexagon(qb);
        hex01n1.addTileToHexagon(beetle3);
        hex10.addTileToHexagon(beetle4);
        hex01.addTileToHexagon(beetle5);
        hexn11.addTileToHexagon(beetle6);
        board.addHexagonToBoard(hex00);
        board.addHexagonToBoard(hex01n1);
        board.addHexagonToBoard(hex10);
        board.addHexagonToBoard(hex01);
        board.addHexagonToBoard(hexn11);
        hive.getPlayer().removeTileFromHand(Hive.Tile.QUEEN_BEE);
        assertThrows(Hive.IllegalMove.class, ()->hive.move(-1, 0,0,-1));
    }

}
