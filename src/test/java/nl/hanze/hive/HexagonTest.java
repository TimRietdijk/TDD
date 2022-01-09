package nl.hanze.hive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HexagonTest {

    @Test
    void whenGetPositionThenListOfQAndR(){
        // ARRANGE
        HexagonClass hexagon = new HexagonClass(0,0);
        // ACT
        List positionArray = hexagon.getPosition();
        List expectedPositionArray = Arrays.asList(0,0);
        // ASSERT
        assertEquals(positionArray, expectedPositionArray);
    }

    @Test
    void givenPieceWhenAddTileToHexagonThenTileAddedToTiles(){
        // ARRANGE
        HexagonClass hexagon = new HexagonClass(0,0);
        Piece piece = new QueenBeeClass();
        // ACT
        hexagon.addTileToHexagon(piece);
        Piece pieceOnBoard = hexagon.getTiles().get(0);
        // ASSERT
        assertEquals(pieceOnBoard, piece);
    }

    @Test
    void whenGetTilesThenTilesOfHexagon(){
        // ARRANGE
        HexagonClass hexagon = new HexagonClass(0,0);
        Piece piece = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        hexagon.addTileToHexagon(piece);
        hexagon.addTileToHexagon(piece2);
        ArrayList<Piece> expectedList = new ArrayList<>();
        expectedList.add(piece);
        expectedList.add(piece2);
        // ACT
        ArrayList<Piece> pieceOnBoard = hexagon.getTiles();
        // ASSERT
        assertEquals(pieceOnBoard, expectedList);
    }

    @Test
    void whenRemoveTopTileThenRemovesHighestTile(){
        // ARRANGE
        HexagonClass hexagon = new HexagonClass(0,0);
        Piece piece = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        hexagon.addTileToHexagon(piece);
        hexagon.addTileToHexagon(piece2);
        ArrayList<Piece> expectedList = new ArrayList<>();
        expectedList.add(piece);
        // ACT
        hexagon.removeTopTile();
        ArrayList<Piece> pieceOnBoard = hexagon.getTiles();
        // ASSERT
        assertEquals(pieceOnBoard, expectedList);
    }
    @Test
    void whenGetHeightThenAmountOfTiles(){
        // ARRANGE
        HexagonClass hexagon = new HexagonClass(0,0);
        Piece piece = new QueenBeeClass();
        Piece piece2 = new BeetleClass();
        hexagon.addTileToHexagon(piece);
        hexagon.addTileToHexagon(piece2);
        // ACT
        int height = hexagon.getHeight();
        // ASSERT
        assertEquals(height, 2);
    }
    @Test
    void whenGetQThenIntPositionQ(){
        // ARRANGE
        HexagonClass hexagon = new HexagonClass(0,0);
        // ACT
        int q = hexagon.getQ();
        // ASSERT
        assertEquals(q, 0);
    }
    @Test
    void whenGetRThenIntPositionR (){
        // ARRANGE
        HexagonClass hexagon = new HexagonClass(0,0);
        // ACT
        int r = hexagon.getQ();
        // ASSERT
        assertEquals(r, 0);
    }
}
