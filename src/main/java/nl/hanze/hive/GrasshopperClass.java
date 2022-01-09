package nl.hanze.hive;

import nl.hanze.hive.Hive;
import nl.hanze.hive.Piece;

import java.util.ArrayList;

public class GrasshopperClass implements Piece {

    private Hive.Player colour;
    private Hive.Tile image;

    public GrasshopperClass(){
        this.image = Hive.Tile.GRASSHOPPER;
    }

    public Hive.Player getColour() {
        return colour;
    }

    public void setColour(Hive.Player colour) {
        this.colour = colour;
    }

    public Hive.Tile getImage() {
        return image;
    }

    private HexagonClass tempHex = null;
    /**
     * Get all moves that a Grasshopper could make in the position of the Piece tile
//     * @param tile Piece
     * @return an ArrayList of all possible moves for this tile
     */
    public ArrayList<HexagonClass> getValidMoves(HexagonClass hexagon) {
//        HexagonClass hexagon = board.getHexagonByTile(this);
        tempHex = null;
        ArrayList<HexagonClass> allEndOfRows = new ArrayList<>();
        allEndOfRows.add(getEndOfDirection(hexagon, 0, -1));
        allEndOfRows.add(getEndOfDirection(hexagon, 1, -1));
        allEndOfRows.add(getEndOfDirection(hexagon, 1, 0));
        allEndOfRows.add(getEndOfDirection(hexagon, 0, 1));
        allEndOfRows.add(getEndOfDirection(hexagon, -1, 1));
        allEndOfRows.add(getEndOfDirection(hexagon, -1, 0));
        allEndOfRows.removeAll(board.getAllNeighborsHexagon(hexagon));
        return allEndOfRows;
    }

    /**
     * Returns the first empty tile in a single direction from a defined base and direction
     //     * @param endOfRow ArrayList of integers representing a position
     //     * @param q Indication of position to move from
     //     * @param r Indication of position to move to
     //     * @param addQ Indication of position representing direction
     //     * @param addR Indication of position representing direction
     * @return the first empty tile of a direction
     */
    private HexagonClass getEndOfDirection(HexagonClass hexagon, int addQ, int addR){
        HexagonClass tempHexagon = board.getHexagonByPosition(hexagon.getQ()+addQ, hexagon.getR()+addR);
        if (tempHexagon.getTiles().size() != 0) {
            getEndOfDirection(tempHexagon, addQ, addR);
        } else {
            tempHex = tempHexagon;
        }
        return tempHex;
    }
}
