package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceFactoryTest {
    @Test
    void givenQueenBeeWhenMakePieceThenNewQueenBeeClass(){
        // ARRANGE
        PieceFactory factory = new PieceFactory();
        // ACT
        Piece piece = factory.makePiece(Hive.Tile.QUEEN_BEE);
        // ASSERT
        assertEquals(piece.getClass(), QueenBeeClass.class);
    }

    @Test
    void givenBeetleWhenMakePieceThenNewBeetleClass(){
        // ARRANGE
        PieceFactory factory = new PieceFactory();
        // ACT
        Piece piece = factory.makePiece(Hive.Tile.BEETLE);
        // ASSERT
        assertEquals(piece.getClass(), BeetleClass.class);
    }

    @Test
    void givenSpiderWhenMakePieceThenNewSpiderClass(){
        // ARRANGE
        PieceFactory factory = new PieceFactory();
        // ACT
        Piece piece = factory.makePiece(Hive.Tile.SPIDER);
        // ASSERT
        assertEquals(piece.getClass(), SpiderClass.class);
    }

    @Test
    void givenSoldierAntWhenMakePieceThenNewSoldierAntClass(){
        // ARRANGE
        PieceFactory factory = new PieceFactory();
        // ACT
        Piece piece = factory.makePiece(Hive.Tile.SOLDIER_ANT);
        // ASSERT
        assertEquals(piece.getClass(), SoldierAntClass.class);
    }

    @Test
    void givenGrasshopperWhenMakePieceThenNewGrasshopperClass(){
        // ARRANGE
        PieceFactory factory = new PieceFactory();
        // ACT
        Piece piece = factory.makePiece(Hive.Tile.GRASSHOPPER);
        // ASSERT
        assertEquals(piece.getClass(), GrasshopperClass.class);
    }
}
