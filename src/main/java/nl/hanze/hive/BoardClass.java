package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The BoardClass Handles the manipulation of the playing field used in HiveClass
 *
 * The playing field (board) is an ArrayList made up of Pieces
 **/

class BoardClass {

    private static BoardClass INSTANCE;
    private ArrayList<HexagonClass> board;
    private BoardClass() { board = new ArrayList<>(); }
    public static BoardClass getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new BoardClass();

        return INSTANCE;
    }

    static void reset() {
        INSTANCE.board.clear();
    }

    void addHexagonToBoard(HexagonClass hexagon) {
        board.add(hexagon);
    }

    HexagonClass getHexagonByPosition(int q, int r){
            for(HexagonClass hexagonOnBoard : board) {
                if (hexagonOnBoard.getPosition().equals(Arrays.asList(q,r))) {
                    return hexagonOnBoard;
                }
            }
        addHexagonToBoard(new HexagonClass(q,r));
        return getHexagonByPosition(q,r);
    }

    ArrayList<HexagonClass> getAllNeighborsHexagon(HexagonClass hexagon) {
        ArrayList<HexagonClass> neighbouringHexagons = new ArrayList<>();
        neighbouringHexagons.add(getHexagonByPosition(hexagon.getQ(),hexagon.getR() - 1));
        neighbouringHexagons.add(getHexagonByPosition(hexagon.getQ() + 1,hexagon.getR() - 1));
        neighbouringHexagons.add(getHexagonByPosition(hexagon.getQ() + 1, hexagon.getR()));
        neighbouringHexagons.add(getHexagonByPosition(hexagon.getQ(),hexagon.getR() + 1));
        neighbouringHexagons.add(getHexagonByPosition(hexagon.getQ()-1,hexagon.getR() + 1));
        neighbouringHexagons.add(getHexagonByPosition(hexagon.getQ() -1,hexagon.getR()));
        return neighbouringHexagons;
    }

    ArrayList<HexagonClass> getNeighbouringHexagonWithTiles(HexagonClass hexagon) {
        ArrayList<HexagonClass> neighboursWithTiles = new ArrayList<>();
        ArrayList<HexagonClass> neighbouringHexagons = getAllNeighborsHexagon(hexagon);
        for (HexagonClass neighbour : neighbouringHexagons){
            if (neighbour.getTiles().size()!=0){
                neighboursWithTiles.add(neighbour);
            }
        }
        return neighboursWithTiles;
    }

    HexagonClass getHexagonByTile(Piece tile){
        for(HexagonClass hexagon : board) {
            for(Piece tileOnHexagon : hexagon.getTiles()){
                if (tileOnHexagon.equals(tile)) {
                    return hexagon;
                }
            }
        }
        return null;
    }

    private ArrayList<Piece> getAllTilesOnTheBoard(){
        ArrayList<Piece> Tiles = new ArrayList<>();
        for(HexagonClass hexagon : board){
            Tiles.addAll(hexagon.getTiles());
        }
        return Tiles;
    }
    boolean isBoardEmpty(){
        return getAllTilesOnTheBoard().size() == 0;
    }

    private ArrayList<HexagonClass> getAllHexagonsWithTiles(){
        ArrayList<HexagonClass> hexagonsWithTiles = new ArrayList<>();
        for( HexagonClass hexagon : board){
            if(hexagon.getTiles().size()>0){
                hexagonsWithTiles.add(hexagon);
            }
        }
        return hexagonsWithTiles;
    }

        /**
     * checks if the board is one island, all tiles are connected
//     * @param toQ Indication of position to move to
//     * @param toR Indication of position to move to
     * @param visited ArrayList of all Piecees already looked at
     * @return true if Piecees on board are all connected
     */
        boolean isOneIsland(HexagonClass toHexagon, ArrayList<HexagonClass> visited) {
        if (getAllHexagonsWithTiles().size() == visited.size()) {
            return true;
        }
        for (HexagonClass neighbour : getNeighbouringHexagonWithTiles(toHexagon)) {
            if (!visited.contains(neighbour)) {
                visited.add(neighbour);
                if (isOneIsland(neighbour, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

        /**
     * get the two common neighbours of two neighboring positions
//     * @param fromQ Indication of position of the
//     * @param fromR Indication of position
//     * @param toQ Indication of position
//     * @param toR Indication of position
     * @return An ArrayList of two ArrayLists each containing the q and r of the common neighbours
     */
    public ArrayList<HexagonClass> getCommonNeighborsHexagons(HexagonClass fromHexagon, HexagonClass toHexagon) {
        ArrayList<HexagonClass> commonNeighbours = new ArrayList<>();

        for (HexagonClass neighbour : getAllNeighborsHexagon(fromHexagon)) {
            for (HexagonClass secondNeighbour : getAllNeighborsHexagon(toHexagon)) {
                if (neighbour.equals(secondNeighbour)) {
                    commonNeighbours.add(secondNeighbour);
                }
            }
        }
        return commonNeighbours;
    }

    int getMinimumZOfCommonNeighbours(ArrayList<HexagonClass> commonNeighbours) {
        return Math.min(commonNeighbours.get(0).getHeight(),commonNeighbours.get(1).getHeight());

    }

    ArrayList<Piece> getTilesOfPlayer(Hive.Player player) {
        ArrayList<Piece> tiles = new ArrayList<>();
        for (Piece tile : getAllTilesOnTheBoard()) {
            if (tile.getColour().equals(player)) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    ArrayList<ArrayList<Piece>> getTilesOfPlayers() {
        ArrayList<ArrayList<Piece>> tiles = new ArrayList<>();
        tiles.add(new ArrayList<>());
        tiles.add(new ArrayList<>());
        for (Piece tile : getAllTilesOnTheBoard()) {
            if (tile.getColour().equals(Hive.Player.WHITE)) {
                tiles.get(0).add(tile);
            }
            if (tile.getColour().equals(Hive.Player.BLACK)) {
                tiles.get(1).add(tile);
            }
        }
        return tiles;
    }

    boolean bothPlayersOnBoard() {
        ArrayList<ArrayList<Piece>> tiles = getTilesOfPlayers();
        return tiles.get(0).size() > 0 && tiles.get(1).size() > 0;
    }
        /**
     * get all the Queen Bees in an ArrayList of Piecees
     * @return an ArrayList containing all the Queen Bees that were presents in given ArrayList
     */
    HexagonClass getQueenBee(ArrayList<Piece> tiles) {
        for (Piece tile : tiles) {
            if (tile.getImage() == Hive.Tile.QUEEN_BEE) {
                return getHexagonByTile(tile);
            }
        }
        return null;
    }
    boolean isTopPieceOnHexagonOfPlayer(HexagonClass hexagon, PlayerClass player){
        for(HexagonClass neighbour : getNeighbouringHexagonWithTiles(hexagon)){
            if (neighbour.getTiles().get(0).getColour() != player.getColour()){
                return false;
            }
        }
        return true;
    }
}