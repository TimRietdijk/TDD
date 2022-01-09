package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void givenHandWhenNumberOfTileInHandWithQueenBeeThenNumberOfQueenBees(){
        // ARRANGE
        PlayerClass player = new PlayerClass(Hive.Player.WHITE);
        // ACT
        int numberOfQueenBees = player.numberOfSpecificTileInHand(Hive.Tile.QUEEN_BEE);
        // ASSERT
        assertEquals(numberOfQueenBees, 1);
    }

    @Test
    void WhenGetColourThenReturnsPlayerColour(){
        // ARRANGE
        PlayerClass player = new PlayerClass(Hive.Player.WHITE);
        // ACT
        Hive.Player playerColour = player.getColour();
        // ASSERT
        assertEquals(playerColour, Hive.Player.WHITE);
    }

    @Test
    void givenIntWhenRemoveTileFromHandThenIntIsNumberOfTileInHandIsDecreased(){
        // ARRANGE
        PlayerClass player = new PlayerClass(Hive.Player.WHITE);
        // ACT
        player.removeTileFromHand(Hive.Tile.QUEEN_BEE);
        int numberOfQueenBeesInHand = player.numberOfSpecificTileInHand(Hive.Tile.QUEEN_BEE);
        // ASSERT
        assertEquals(numberOfQueenBeesInHand, 0);
    }

    @Test
    void whenGetNumberOfPiecesInHandThenNumberUnplayedPieces(){
        // ARRANGE
        PlayerClass player = new PlayerClass(Hive.Player.WHITE);
        // ACT
        int numberOfQueenBeesInHand = player.getNumberOfPiecesInHand();
        // ASSERT
        assertEquals(numberOfQueenBeesInHand, 11);
    }

    @Test
    void whenHasPlayedQueenBeeThenFalse(){
        // ARRANGE
        PlayerClass player = new PlayerClass(Hive.Player.WHITE);
        // ACT
        boolean hasPlayedQB = player.hasPlayedQB();
        // ASSERT
        assertFalse(hasPlayedQB);
    }
}
