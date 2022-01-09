package nl.hanze.hive;

import java.util.ArrayList;

public class QueenBeeClass implements Piece {

    private Hive.Player colour;
    private Hive.Tile image;

    public QueenBeeClass(){
        this.image = Hive.Tile.QUEEN_BEE;
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
     * Get all moves that a Queen bee could make in the position of the Piece tile
//     * @param tile Piece
     * @return an ArrayList of all possible moves for this tile
     */
    public ArrayList<HexagonClass> getValidMoves(HexagonClass hexagon){
        ArrayList<HexagonClass> validMoves = new ArrayList<>();
        for (HexagonClass neighbour : board.getAllNeighborsHexagon(hexagon)) {
            if (neighbour.getHeight()==0 && canBePushed(hexagon, neighbour)){
                validMoves.add(neighbour);
            }
        }
        return validMoves;
    }
}
