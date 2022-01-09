package nl.hanze.hive;

import java.util.EnumMap;

/**
 * Class representing a Player
 * There should only be 2 players, black and white
 * Each player has a colour and a hand
 * A hand contains Pieces (or tiles)
 */

public class PlayerClass {

    private Hive.Player colour;
    private EnumMap<Hive.Tile, Integer> hand = new EnumMap<>(Hive.Tile.class);

    /**
     * Constructor for a PlayerClass
     * Instantiating the hand with tiles
     * @param colour Hive.Player.WHITE/BLACK
     */

    public PlayerClass(Hive.Player colour){
        this.colour = colour;
        hand.put(Hive.Tile.QUEEN_BEE, 1);
        hand.put(Hive.Tile.GRASSHOPPER, 3);
        hand.put(Hive.Tile.SOLDIER_ANT, 3);
        hand.put(Hive.Tile.SPIDER, 2);
        hand.put(Hive.Tile.BEETLE, 2);
    }

    /**
     * Get the colour of the corresponding PlayerClass object
     * @return the colour, Hive.Player.WHITE/BLACK
     */
    public Hive.Player getColour() {
        return colour;
    }

    /**
     * Gets the amount of tiles left in PlayerClass's hand
     * @param tile Piece
     * @return the amount of tiles left in hand based on the tile image
     */
    public int numberOfSpecificTileInHand(Hive.Tile tile){
        return hand.get(tile);
    }

    /**
     * Removes one tile from hand if hand contains more than 0
     * @param tile tile to pop from the hand
     */
    public void removeTileFromHand(Hive.Tile tile){
        hand.put(tile, hand.get(tile) - 1);
    }

    public int getNumberOfPiecesInHand(){
        int count = 0;
        for(int numberOfPiece: hand.values()){
            count = count + numberOfPiece;
        }
        return count;
    }
    public boolean hasPlayedQB(){
        return this.numberOfSpecificTileInHand(Hive.Tile.QUEEN_BEE) < 1;
    }

}
