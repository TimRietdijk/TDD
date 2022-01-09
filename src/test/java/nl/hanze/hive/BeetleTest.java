package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BeetleTest {
    @Test
    void canNotMoveMoreThan1Space() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(0,0,-2,0));
    }

    @Test
    void canMoveToAnOccupiedHexagon() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,2,-1);       //Black
        // ASSERT
        assertDoesNotThrow(()->hive.move(0,0,1,-1));
    }

    @Test
    void canNotMoveToHexagonWithoutNeighbours() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(0,0,-1,0));
    }

    @Test
    void canNotMakeAMoveWhereYouDoNotStayInContactWithOtherPieces() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(0,0,0,1));
    }

    @Test
    void canNotSlipInBetweenTwoPieces() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(0,0,1,0));
    }

    @Test
    void legalMove() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.BEETLE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        // ASSERT
        assertDoesNotThrow(()->hive.move(0,0,0,-1));
    }

    @Test
    void pushedPassedHighCommonNeighbours() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.QUEEN_BEE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 0,1);        //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 1,-1);     //White
        hive.play(Hive.Tile.BEETLE, 1,1);           //Black
        hive.play(Hive.Tile.BEETLE, 0,-1);          //White
        hive.play(Hive.Tile.BEETLE, -1,2);          //Black
        hive.play(Hive.Tile.BEETLE, 2,-2);          //White
        hive.move(-1,2,0,2);       //Black
        hive.move(0,-1,0,0);       //White
        hive.move(1,1,0,2);      //Black
        BoardClass board = BoardClass.getInstance();
//        hive.move(0,0,1,0);       //White
//        hive.move(1,1,0,2);      //Black
        // ASSERT
        assertDoesNotThrow(()->hive.move(0,0,1,0));
    }

    @Test
    void commonNeighboursHigherThanAB() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.QUEEN_BEE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 0,1);        //Black
        hive.play(Hive.Tile.BEETLE, 0,-1);     //White
        hive.play(Hive.Tile.BEETLE, -1,2);           //Black
        hive.play(Hive.Tile.BEETLE, 1,-1);          //White
        hive.play(Hive.Tile.BEETLE, 0,2);          //Black
        hive.move(0,-1,0,0);       //White
        hive.move(-1,2,0,1);       //Black
        hive.move(1,-1,0,0);        //White
        hive.move(0,2,0,1);      //Black
        // ASSERT
        assertDoesNotThrow(()->hive.move(0,0,0,1));
    }
}
