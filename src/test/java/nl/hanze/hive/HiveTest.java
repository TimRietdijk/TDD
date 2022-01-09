package nl.hanze.hive;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HiveTest {

    @Test
    void whenGetPlayerThenPlayerClassWhite() {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        Hive.Player colour = hive.getPlayer().getColour();
        // ASSERT
        assertEquals(colour, Hive.Player.WHITE);
    }

    @Test
    void givenTileAndPositionQRWhenPlayThenAddsTileToHexagonWithPositionQR() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.QUEEN_BEE, 0, 0);
        BoardClass board = BoardClass.getInstance();
        Piece piece = board.getHexagonByPosition(0, 0).getTiles().get(0);
        // ASSERT
        assertEquals(piece.getClass(), QueenBeeClass.class);
    }

    @Test
    void givenScenarioWhenIsTopPieceOnHexagonOfPlayerInPlayThenThrowsIllegalMove() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        hive.play(Hive.Tile.BEETLE, 0, 1);
        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.play(Hive.Tile.BEETLE, 1, 1));
    }

    @Test
    void givenScenarioWhenHexagonIsNotEmptyInPlayThenThrowsIllegalMove() throws Hive.IllegalMove  {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.play(Hive.Tile.BEETLE, 0, 0));
    }

    @Test
    void givenScenarioWhenNotEnoughOfTileInHandInPlayThenThrowsIllegalMove() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.QUEEN_BEE, 0, 0);
        hive.play(Hive.Tile.QUEEN_BEE, 0, 1);
        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.play(Hive.Tile.QUEEN_BEE, 0, -1));
    }

    @Test
    void givenScenarioWhenGetNeighbouringHexagonWithTilesInPlayThenThrowsIllegalMove() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.play(Hive.Tile.BEETLE, 2, 0));
    }

    @Test
    void givenScenarioWhenNotQueenBeeOnBoardAfterThreeTilesInPlayThenThrowsIllegalMove() throws Hive.IllegalMove  {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        hive.play(Hive.Tile.BEETLE, 0, 1);
        hive.play(Hive.Tile.BEETLE, 0, -1);
        hive.play(Hive.Tile.BEETLE, 1, 1);
        hive.play(Hive.Tile.SOLDIER_ANT, 1, -1);
        hive.play(Hive.Tile.SOLDIER_ANT, 0, 2);

        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.play(Hive.Tile.SOLDIER_ANT, 2, -2));
    }

    @Test
    void givenScenarioWhenPlayThenSucceed() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        hive.play(Hive.Tile.QUEEN_BEE, 0, 1);
        hive.play(Hive.Tile.BEETLE, 1, -1);
        hive.play(Hive.Tile.BEETLE, 0, 2);
        hive.play(Hive.Tile.SOLDIER_ANT, 1, -2);
        hive.play(Hive.Tile.SOLDIER_ANT, 1, 2);
        // ASSERT
        assertDoesNotThrow(() -> hive.play(Hive.Tile.QUEEN_BEE, 0, -1));
    }

    @Test
    void checkIfPassOnlyWorksWhenPlayerHasNoMovesLeft() {
        // ARRANGE
        PieceFactory factory = new PieceFactory();
        HiveClass hive = new HiveClass();
        BoardClass board = BoardClass.getInstance();
        // ACT
        Piece tile = factory.makePiece(Hive.Tile.QUEEN_BEE);
        Piece tile2 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile3 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile4 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile5 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile6 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile7 = factory.makePiece(Hive.Tile.BEETLE);

        tile.setColour(Hive.Player.WHITE);
        tile2.setColour(Hive.Player.BLACK);
        tile3.setColour(Hive.Player.BLACK);
        tile4.setColour(Hive.Player.BLACK);
        tile5.setColour(Hive.Player.BLACK);
        tile6.setColour(Hive.Player.BLACK);
        tile7.setColour(Hive.Player.BLACK);

        board.getHexagonByPosition(0, 0).addTileToHexagon(tile);
        board.getHexagonByPosition(0, -1).addTileToHexagon(tile6);
        board.getHexagonByPosition(1, -1).addTileToHexagon(tile2);
        board.getHexagonByPosition(1, 0).addTileToHexagon(tile3);
        board.getHexagonByPosition(0, 1).addTileToHexagon(tile4);
        board.getHexagonByPosition(-1, 1).addTileToHexagon(tile5);
        board.getHexagonByPosition(-1, 0).addTileToHexagon(tile7);
        // ASSERT
        assertDoesNotThrow(() -> hive.pass());
    }

    @Test
    void givenScenarioWhenIsWinnerThenSucceed() throws Hive.IllegalMove {
        // ARRANGE
        PieceFactory factory = new PieceFactory();
        HiveClass hive = new HiveClass();
        BoardClass board = BoardClass.getInstance();
        // ACT
        Piece tile = factory.makePiece(Hive.Tile.QUEEN_BEE);
        Piece tile2 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile3 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile4 = factory.makePiece(Hive.Tile.GRASSHOPPER);
        Piece tile5 = factory.makePiece(Hive.Tile.GRASSHOPPER);
        Piece tile6 = factory.makePiece(Hive.Tile.SPIDER);
        Piece tile7 = factory.makePiece(Hive.Tile.BEETLE);

        tile.setColour(Hive.Player.WHITE);
        tile2.setColour(Hive.Player.BLACK);
        tile3.setColour(Hive.Player.BLACK);
        tile4.setColour(Hive.Player.BLACK);
        tile5.setColour(Hive.Player.BLACK);
        tile6.setColour(Hive.Player.BLACK);
        tile7.setColour(Hive.Player.BLACK);

        board.getHexagonByPosition(-1, 0).addTileToHexagon(tile);
        board.getHexagonByPosition(-1, 1).addTileToHexagon(tile6);
        board.getHexagonByPosition(-2, 1).addTileToHexagon(tile2);
        board.getHexagonByPosition(-2, 0).addTileToHexagon(tile3);
        board.getHexagonByPosition(-1, -1).addTileToHexagon(tile4);
        board.getHexagonByPosition(0, -1).addTileToHexagon(tile5);
        board.getHexagonByPosition(1, -1).addTileToHexagon(tile7);

        // ASSERT
        hive.isWinner(Hive.Player.BLACK);
        hive.nextTurn();
        hive.getPlayer().removeTileFromHand(Hive.Tile.QUEEN_BEE);
        hive.move(1, -1, 0, 0);
        assertTrue(hive.isWinner(Hive.Player.BLACK));
    }

    @Test
    void givenSecondScenarioWhenIsWinnerThenSucceed() throws Hive.IllegalMove {
        // ARRANGE
        PieceFactory factory = new PieceFactory();
        HiveClass hive = new HiveClass();
        BoardClass board = BoardClass.getInstance();
        // ACT
        Piece tile = factory.makePiece(Hive.Tile.QUEEN_BEE);
        Piece tile2 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile3 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile4 = factory.makePiece(Hive.Tile.GRASSHOPPER);
        Piece tile5 = factory.makePiece(Hive.Tile.GRASSHOPPER);
        Piece tile6 = factory.makePiece(Hive.Tile.SPIDER);
        Piece tile7 = factory.makePiece(Hive.Tile.BEETLE);

        tile.setColour(Hive.Player.WHITE);
        tile2.setColour(Hive.Player.BLACK);
        tile3.setColour(Hive.Player.BLACK);
        tile4.setColour(Hive.Player.BLACK);
        tile5.setColour(Hive.Player.BLACK);
        tile6.setColour(Hive.Player.BLACK);
        tile7.setColour(Hive.Player.BLACK);

        board.getHexagonByPosition(-1, 0).addTileToHexagon(tile);
        board.getHexagonByPosition(-1, 1).addTileToHexagon(tile6);
        board.getHexagonByPosition(-2, 1).addTileToHexagon(tile2);
        board.getHexagonByPosition(-2, 0).addTileToHexagon(tile3);
        board.getHexagonByPosition(-1, -1).addTileToHexagon(tile4);
        board.getHexagonByPosition(0, -1).addTileToHexagon(tile5);
        board.getHexagonByPosition(0, 0).addTileToHexagon(tile7);

        // ASSERT
        assertTrue(hive.isWinner(Hive.Player.BLACK));
    }

    @Test
    void givenScenarioWhenIsDrawThenFalse() {
        // ARRANGE
        PieceFactory factory = new PieceFactory();
        HiveClass hive = new HiveClass();
        BoardClass board = BoardClass.getInstance();
        // ACT
        Piece tile = factory.makePiece(Hive.Tile.QUEEN_BEE);
        Piece tile2 = factory.makePiece(Hive.Tile.QUEEN_BEE);
        Piece tile3 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile4 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile5 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile6 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile7 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile8 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile9 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile10 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile11 = factory.makePiece(Hive.Tile.BEETLE);
        Piece tile12 = factory.makePiece(Hive.Tile.BEETLE);

        tile.setColour(Hive.Player.WHITE);
        tile2.setColour(Hive.Player.BLACK);
        tile3.setColour(Hive.Player.BLACK);
        tile4.setColour(Hive.Player.BLACK);
        tile5.setColour(Hive.Player.BLACK);
        tile6.setColour(Hive.Player.BLACK);
        tile7.setColour(Hive.Player.BLACK);
        tile8.setColour(Hive.Player.BLACK);
        tile9.setColour(Hive.Player.BLACK);
        tile10.setColour(Hive.Player.BLACK);
        tile11.setColour(Hive.Player.BLACK);
        tile12.setColour(Hive.Player.BLACK);

        board.getHexagonByPosition(-1, 0).addTileToHexagon(tile);
        board.getHexagonByPosition(1, 0).addTileToHexagon(tile2);
        board.getHexagonByPosition(2, -1).addTileToHexagon(tile3);
        board.getHexagonByPosition(2, 0).addTileToHexagon(tile4);
        board.getHexagonByPosition(1, 1).addTileToHexagon(tile5);
        board.getHexagonByPosition(0, 1).addTileToHexagon(tile6);
        board.getHexagonByPosition(-1, 1).addTileToHexagon(tile7);
        board.getHexagonByPosition(-2, 1).addTileToHexagon(tile8);
        board.getHexagonByPosition(-2, 0).addTileToHexagon(tile9);
        board.getHexagonByPosition(-1, -1).addTileToHexagon(tile10);
        board.getHexagonByPosition(0, -1).addTileToHexagon(tile11);
        board.getHexagonByPosition(1, -1).addTileToHexagon(tile12);

        // ASSERT
        assertFalse(hive.isDraw());
    }

    @Test
    void WhenMoveToSamePositionInMoveThenThrowsIllegalMove() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        hive.play(Hive.Tile.BEETLE, 1, 0);
        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.move(0,0,0,0));
    }

    @Test
    void givenScenarioWhenGetNeighbouringHexagonWithTilesInMoveThenThrowsIllegalMove() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        hive.play(Hive.Tile.BEETLE, 1, 0);
        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.move(1,0,2,0));
    }

    @Test
    void givenScenarioWhenGetHexagonByPositionInMoveThenThrowsIllegalMove() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        hive.play(Hive.Tile.BEETLE, 1, 0);
        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.move(0,-1,1,-1));
    }

    @Test
    void moveScenario4() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        hive.play(Hive.Tile.BEETLE, 1, 0);
        hive.play(Hive.Tile.BEETLE, 0, -1);
        hive.play(Hive.Tile.SOLDIER_ANT, 2, -1);
        hive.play(Hive.Tile.QUEEN_BEE, -1, 0);
        hive.play(Hive.Tile.QUEEN_BEE, 2, -2);
        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.move(0,-1,1,-2));
    }

    @Test
    void moveScenario5() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0, 0);
        hive.play(Hive.Tile.BEETLE, 1, 0);
        hive.play(Hive.Tile.BEETLE, 0, -1);
        hive.play(Hive.Tile.SOLDIER_ANT, 2, -1);
        hive.play(Hive.Tile.QUEEN_BEE, -1, 0);
        hive.play(Hive.Tile.QUEEN_BEE, 2, -2);
        // ASSERT
        assertThrows(Hive.IllegalMove.class, () -> hive.move(0,-1,0,-2));
    }
}