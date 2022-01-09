package nl.hanze.hive;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SoldierAntTest {
    @Test
    void canNotMoveToStartingPosition() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.SOLDIER_ANT, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(0,0,0,0));
    }

    @Test
    void canNotMovePositionWithoutNeighbours() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.SOLDIER_ANT, 0,0);        //White
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
    void canNotMoveInBetweenTwoOccupiedHexagons() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.SOLDIER_ANT, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(0,0,1,0));
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
        hive.play(Hive.Tile.SOLDIER_ANT, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(1,1,3,-1));
    }

    @Test
    void canMove() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.SOLDIER_ANT, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.QUEEN_BEE, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        // ASSERT
        assertDoesNotThrow(()->hive.move(0,0,3,-2));
    }
}
