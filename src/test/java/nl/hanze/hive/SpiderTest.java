package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpiderTest {
    @Test
    void canNotMoveLessThan3Hexagons() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.SPIDER, 0,0);           //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);          //Black
        hive.move(0,1,2,0);       //White
        hive.move(3,-1,3,0);      //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(0,0,0,-1));
    }

    @Test
    void canNotMoveMoreThan3Hexagons() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.SPIDER, 0,0);           //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);          //Black
        hive.move(0,1,2,0);       //White
        hive.move(3,-1,3,0);      //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(0,0,3,-1));
    }

    @Test
    void canNotMoveInBetweenTwoOccupiedHexagons() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.SPIDER, 1,0);           //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-2);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-2);          //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(1,0,1,-2));
    }

    @Test
    void canNotMoveToOccupiedHexagon() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.QUEEN_BEE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.SPIDER, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(1,1,3,-1));
    }


    @Test
    void canMove3Hexagons() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.SPIDER, 0,0);           //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);          //Black
        hive.move(0,1,2,0);       //White
        hive.move(3,-1,3,0);      //Black
        // ASSERT
        assertDoesNotThrow(()->hive.move(0,0,2,-2));
    }
}
