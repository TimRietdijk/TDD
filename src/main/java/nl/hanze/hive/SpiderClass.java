package nl.hanze.hive;

import nl.hanze.hive.Hive;
import nl.hanze.hive.Piece;

import java.util.ArrayList;

public class SpiderClass implements Piece {

    private Hive.Player colour;
    private Hive.Tile image;

    public SpiderClass(){
        this.image = Hive.Tile.SPIDER;
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
     * Get all moves that a Spider bee could make in the position of the Piece tile
//     * @param tile Piece
     * @return an ArrayList of all possible moves for this tile
     */
    public ArrayList<HexagonClass> getValidMoves(HexagonClass hexagon){
//        HexagonClass hexagon = board.getHexagonByTile(this);
        validMoves.clear();
        spiderCanMove(hexagon, new ArrayList<>(), 3);
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
     * DFS function to find all valid moves for spider
     //     * @param nextQ Indication of position to check, first time it is the tiles current position
     //     * @param nextR Indication of position to check, first time it is the tiles current position
     //     * @param visited ArrayList keeping track of all positions visited
     //     * @param depth integer to make sure we only add validmoves after 3 tiles along the way
     * @return false after finding all validmoves
     */
    private boolean spiderCanMove(HexagonClass nextHexagon, ArrayList<HexagonClass> visited, int depth){
        if(depth <= 0){
            validMoves.add(nextHexagon);
            return false;
        }
        visited.add(nextHexagon);
        for (HexagonClass neighbourPosition: board.getAllNeighborsHexagon(nextHexagon)) {
            if (neighbourPosition.getTiles().size() == 0
                    && board.getNeighbouringHexagonWithTiles(neighbourPosition).size() > 0
                    && canBePushed(nextHexagon, neighbourPosition)
                    && !visited.contains(neighbourPosition)) {

                if(spiderCanMove(neighbourPosition, visited, depth-1)){
                    return true;
                }
            }
        }
        return false;
    }
}
