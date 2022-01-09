package nl.hanze.hive;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BoardTest {

    @Test
    void testSingletonBehavior(){
        // ARRANGE

        // ACT
        BoardClass board = BoardClass.getInstance();
        // ASSERT
        assertEquals(board.getClass(), BoardClass.class);
    }

    @Test
    void testBoardResetBehavior(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        HexagonClass hexagon = new HexagonClass(0,0);
        hexagon.addTileToHexagon(new QueenBeeClass());
        board.addHexagonToBoard(hexagon);
        // ACT
        board.reset();
        // ASSERT
        assertTrue(board.isBoardEmpty());
    }

    @Test
    void addHexagonToBoard(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        // ACT
        board.addHexagonToBoard(hexagon);
        HexagonClass testHexagon = board.getHexagonByPosition(0,0);
        // ASSERT
        assertEquals(testHexagon, hexagon);
    }

    @Test
    void getHexagonByPosition(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        board.addHexagonToBoard(hexagon);
        // ACT
        List testHexagon = board.getHexagonByPosition(0,0).getPosition();
        List position = Arrays.asList(0,0);
        // ASSERT
        assertEquals(testHexagon, position);
    }

    @Test
    void getAllNeighborsHexagon(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        ArrayList<HexagonClass> expectedNeighbours = new ArrayList<>();
        expectedNeighbours.add(neighbourHexagon6);
        expectedNeighbours.add(neighbourHexagon1);
        expectedNeighbours.add(neighbourHexagon2);
        expectedNeighbours.add(neighbourHexagon3);
        expectedNeighbours.add(neighbourHexagon4);
        expectedNeighbours.add(neighbourHexagon5);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT
        ArrayList<HexagonClass> neighbours = board.getAllNeighborsHexagon(hexagon);
        // ASSERT
        assertEquals(neighbours, expectedNeighbours);
    }

    @Test
    void getNeighbouringHexagonWithTiles(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        neighbourHexagon1.addTileToHexagon(piece1);
        neighbourHexagon5.addTileToHexagon(piece2);
        ArrayList<HexagonClass> expectedNeighbours = new ArrayList<>();
        expectedNeighbours.add(neighbourHexagon1);
        expectedNeighbours.add(neighbourHexagon5);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT
        ArrayList<HexagonClass> neighboursWithTiles = board.getNeighbouringHexagonWithTiles(hexagon);
        // ASSERT
        assertEquals(neighboursWithTiles, expectedNeighbours);
    }

    @Test
    void getHexagonByTile(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        neighbourHexagon1.addTileToHexagon(piece1);
        neighbourHexagon5.addTileToHexagon(piece2);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT
        HexagonClass hexagonWithSpecificTile = board.getHexagonByTile(piece1);
        // ASSERT
        assertEquals(neighbourHexagon1, hexagonWithSpecificTile);
    }

    @Test
    void isOneIsland(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        neighbourHexagon1.addTileToHexagon(piece1);
        neighbourHexagon2.addTileToHexagon(piece2);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT

        // ASSERT
        assertTrue(board.isOneIsland(neighbourHexagon1, new ArrayList<>()));
    }

    @Test
    void getCommonNeighborsHexagons(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        ArrayList<HexagonClass> expectedArray = new ArrayList<>();
        expectedArray.add(board.getHexagonByPosition(2,-1));
        expectedArray.add(board.getHexagonByPosition(0,0));
        neighbourHexagon1.addTileToHexagon(piece1);
        neighbourHexagon2.addTileToHexagon(piece2);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT
        ArrayList<HexagonClass> commonNeighbours = board.getCommonNeighborsHexagons(neighbourHexagon1, neighbourHexagon2);
        // ASSERT
        assertEquals(commonNeighbours, expectedArray);
    }

    @Test
    void getMinimumZOfCommonNeighbours(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        ArrayList<HexagonClass> expectedArray = new ArrayList<>();
        expectedArray.add(board.getHexagonByPosition(2,-1));
        expectedArray.add(board.getHexagonByPosition(0,0));
        neighbourHexagon1.addTileToHexagon(piece1);
        neighbourHexagon2.addTileToHexagon(piece2);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT
        int minHeightOfCommonNeighbours = board.getMinimumZOfCommonNeighbours(expectedArray);
        // ASSERT
        assertEquals(0, minHeightOfCommonNeighbours);
    }

    @Test
    void getTilesOfPlayer(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        piece1.setColour(Hive.Player.WHITE);
        piece2.setColour(Hive.Player.BLACK);
        ArrayList<Piece> expectedArray = new ArrayList<>();
        expectedArray.add(piece1);
        neighbourHexagon1.addTileToHexagon(piece1);
        neighbourHexagon2.addTileToHexagon(piece2);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT
        ArrayList<Piece> whiteTiles = board.getTilesOfPlayer(Hive.Player.WHITE);
        // ASSERT
        assertEquals(expectedArray, whiteTiles);
    }

    @Test
    void getTilesOfPlayers(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        piece1.setColour(Hive.Player.WHITE);
        piece2.setColour(Hive.Player.BLACK);
        ArrayList<ArrayList<Piece>> expectedArray = new ArrayList<>();
        ArrayList<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(piece1);
        ArrayList<Piece> blackPieces = new ArrayList<>();
        blackPieces.add(piece2);
        expectedArray.add(whitePieces);
        expectedArray.add(blackPieces);
        neighbourHexagon1.addTileToHexagon(piece1);
        neighbourHexagon2.addTileToHexagon(piece2);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT
        ArrayList<ArrayList<Piece>> allPlayerTiles = board.getTilesOfPlayers();
        // ASSERT
        assertEquals(expectedArray, allPlayerTiles);
    }

    @Test
    void bothPlayersOnBoard(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        piece1.setColour(Hive.Player.WHITE);
        piece2.setColour(Hive.Player.BLACK);
        neighbourHexagon1.addTileToHexagon(piece1);
        neighbourHexagon2.addTileToHexagon(piece2);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT

        // ASSERT
        assertTrue(board.bothPlayersOnBoard());
    }

    @Test
    void getQueenBee(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        piece1.setColour(Hive.Player.WHITE);
        piece2.setColour(Hive.Player.BLACK);
        neighbourHexagon1.addTileToHexagon(piece1);
        neighbourHexagon2.addTileToHexagon(piece2);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT
        HexagonClass hexagonWithQueenBee = board.getQueenBee(board.getTilesOfPlayer(Hive.Player.WHITE));
        // ASSERT
        assertEquals(neighbourHexagon1, hexagonWithQueenBee);
    }

    @Test
    void isTopPieceOnHexagonOfPlayer(){
        // ARRANGE
        BoardClass board = BoardClass.getInstance();
        board.reset();
        PlayerClass white = new PlayerClass(Hive.Player.WHITE);
        HexagonClass hexagon = new HexagonClass(0,0);
        HexagonClass neighbourHexagon1 = new HexagonClass(1,-1);
        HexagonClass neighbourHexagon2 = new HexagonClass(1,0);
        HexagonClass neighbourHexagon3 = new HexagonClass(0,1);
        HexagonClass neighbourHexagon4 = new HexagonClass(-1,1);
        HexagonClass neighbourHexagon5 = new HexagonClass(-1,0);
        HexagonClass neighbourHexagon6 = new HexagonClass(0,-1);
        Piece piece1 = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        piece1.setColour(Hive.Player.WHITE);
        piece2.setColour(Hive.Player.BLACK);
        neighbourHexagon1.addTileToHexagon(piece2);
        neighbourHexagon1.addTileToHexagon(piece1);
        board.addHexagonToBoard(hexagon);
        board.addHexagonToBoard(neighbourHexagon1);
        board.addHexagonToBoard(neighbourHexagon2);
        board.addHexagonToBoard(neighbourHexagon3);
        board.addHexagonToBoard(neighbourHexagon4);
        board.addHexagonToBoard(neighbourHexagon5);
        board.addHexagonToBoard(neighbourHexagon6);
        // ACT

        // ASSERT
        assertTrue(board.isTopPieceOnHexagonOfPlayer(neighbourHexagon1, white));
    }
}
