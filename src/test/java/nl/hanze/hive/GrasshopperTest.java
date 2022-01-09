package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GrasshopperTest {
    @Test
    void canNotMoveJustOneHexagon() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.QUEEN_BEE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.GRASSHOPPER, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(1,1,1,0));
    }

    @Test
    void canNotJumpOverEmptyHexagon() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.QUEEN_BEE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.GRASSHOPPER, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(1,1,1,-2));
    }

    @Test
    void canNotMoveToHexagonThatIsNotDiagonallyFromGrasshopper() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.QUEEN_BEE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.GRASSHOPPER, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        // ASSERT
        assertThrows(Hive.IllegalMove.class, ()->hive.move(1,1,3,-2));
    }

    @Test
    void canMoveToFirstEmptyDiagonalHexagonThatIsNotANeighbour() throws Hive.IllegalMove {
        // ARRANGE
        HiveClass hive = new HiveClass();
        // ACT
        hive.play(Hive.Tile.QUEEN_BEE, 0,0);        //White
        hive.play(Hive.Tile.QUEEN_BEE, 1,-1);       //Black
        hive.play(Hive.Tile.SOLDIER_ANT, 0,1);      //White
        hive.play(Hive.Tile.SOLDIER_ANT, 2,-1);     //Black
        hive.play(Hive.Tile.GRASSHOPPER, 1,1);      //White
        hive.play(Hive.Tile.BEETLE, 3,-1);           //Black
        hive.move(0,1,2,0);        //White
        hive.move(3,-1,3,0);       //Black
        hive.move(0,0,0,-1);        //White
        hive.move(3,0,3,-1);       //Black
        // ASSERT
        assertDoesNotThrow(()->hive.move(1,1,4,-2));
    }
}
