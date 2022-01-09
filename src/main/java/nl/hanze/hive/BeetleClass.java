package nl.hanze.hive;

import java.util.ArrayList;

public class BeetleClass implements Piece {

    private Hive.Player colour;
    private Hive.Tile image;

    public BeetleClass(){
        this.image = Hive.Tile.BEETLE;
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

    private boolean canBePushed(HexagonClass fromHexagon, HexagonClass toHexagon) {
        ArrayList<HexagonClass> commonNeighbors = board.getCommonNeighborsHexagons(fromHexagon, toHexagon);
        if(toHexagon.getHeight() == fromHexagon.getHeight()){
            if(commonNeighbors.get(0).getHeight() == 0 && commonNeighbors.get(1).getHeight() == 0){
                return false;
            }
        }
        ArrayList<HexagonClass> commonNeighbours = board.getCommonNeighborsHexagons(fromHexagon, toHexagon);
        if (board.getAllNeighborsHexagon(fromHexagon).contains(toHexagon)) {
            int minNeighbours = board.getMinimumZOfCommonNeighbours(commonNeighbours);
            int maxZPush = Math.max(fromHexagon.getHeight(), toHexagon.getHeight());
            return minNeighbours <= maxZPush;
        }
        return false;
    }

    /**
     * Get all moves that a Beetle could make in the position of the Piece tile
//     * @param tile Piece
     * @return an ArrayList of all possible moves for this tile
     */
    public ArrayList<HexagonClass> getValidMoves(HexagonClass hexagon){
        ArrayList<HexagonClass> validMoves = new ArrayList<>();
        for (HexagonClass neighbour : board.getAllNeighborsHexagon(hexagon)) {
            if (canBePushed(hexagon, neighbour)){
                validMoves.add(neighbour);
            }
        }
        return validMoves;
    }
}
