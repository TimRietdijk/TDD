package nl.hanze.hive;

import nl.hanze.hive.Hive;
import nl.hanze.hive.Piece;

import java.util.ArrayList;

public class SoldierAntClass implements Piece {

    private Hive.Player colour;
    private Hive.Tile image;

    public SoldierAntClass(){
        this.image = Hive.Tile.SOLDIER_ANT;
    }

    public Hive.Player getColour() {
        return colour;
    }

    ArrayList<HexagonClass> validMoves = new ArrayList<>();

    public void setColour(Hive.Player colour) {
        this.colour = colour;
    }

    public Hive.Tile getImage() {
        return image;
    }

    /**
     * Get all moves that a Soldier ant could make in the position of the Piece tile
//     * @param tile Piece
     * @return an ArrayList of all possible moves for this tile
     */
    public ArrayList<HexagonClass> getValidMoves(HexagonClass hexagon){
        validMoves.clear();
//        HexagonClass hexagon = board.getHexagonByTile(this);
        soldierAntCanMove(hexagon);
        return validMoves;
    }

    boolean canBePushed(HexagonClass fromHexagon, HexagonClass toHexagon) {
        ArrayList<HexagonClass> commonNeighbors = board.getCommonNeighborsHexagons(fromHexagon, toHexagon);
        if(commonNeighbors.get(0).getHeight() == 0 && commonNeighbors.get(1).getHeight() == 0){
            return false;
        }
        if(commonNeighbors.get(0).getHeight() > 0 && commonNeighbors.get(1).getHeight() > 0) {
            return false;
        }
        return true;
    }

    /**
     * Adds all valid moves of tile in position to validMoves
     //     * @param nextQ Indication of position to move to, first time it is the tiles current position
     //     * @param nextR Indication of position to move to, first time it is the tiles current position
     * @return false after finding all valid moves
     */
    private boolean soldierAntCanMove(HexagonClass nextHexagon){
        for (HexagonClass neighbourPosition: board.getAllNeighborsHexagon(nextHexagon)) {
            if (neighbourPosition.getTiles().size() == 0
                    && board.getNeighbouringHexagonWithTiles(neighbourPosition).size() > 0
                    && canBePushed(nextHexagon, neighbourPosition)
                    && !validMoves.contains(neighbourPosition)) {

                validMoves.add(neighbourPosition);
                if (soldierAntCanMove(neighbourPosition)) {
                    return true;
                }
            }
        }

        return false;
    }
}
