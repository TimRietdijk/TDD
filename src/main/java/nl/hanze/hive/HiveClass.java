package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Arrays;

/**
* The HiveClass class is used for all game logic and a central hub regarding this game.
* It makes use of the Hive interface, enforcing certain functions and parameters.
 **/

public class HiveClass implements Hive{

    private PlayerClass playerWhite;
    private PlayerClass playerBlack;
    private Hive.Player currentPlayer;
    private BoardClass board;
    private PieceFactory pieceFactory;

    /**
     * constructor for HiveClass, instantiating all necessary values in all HiveClasses.
     */
    public HiveClass(){
        playerWhite = new PlayerClass(Hive.Player.WHITE);
        playerBlack = new PlayerClass(Hive.Player.BLACK);
        currentPlayer = playerWhite.getColour();
        board = BoardClass.getInstance();
        board.reset();
        pieceFactory = new PieceFactory();
    }

    /**
     * Gets the currentPlayer
     * @return the PlayerClass corresponding with currentPlayer
     */
    public PlayerClass getPlayer() {
        switch (currentPlayer) {
            case WHITE:
                return playerWhite;
            case BLACK:
                return playerBlack;
        }
        return null;
    }

    /**
     * Places a new Tile on the board ArrayList in position q,r
//     * @param tile Piece
     * @param q Indication of position
     * @param r Indication of position
     * @throws IllegalMove if one or more required condition is false
     */
    public void play(Tile image, int q, int r) throws IllegalMove{
        Piece tile = pieceFactory.makePiece(image);
        tile.setColour(getPlayer().getColour());
        HexagonClass tempHexagon = board.getHexagonByPosition(q,r);

        if(board.isBoardEmpty()){
            handlePlay(tempHexagon, tile);
        } else if(board.bothPlayersOnBoard()) {
            if (getPlayer().numberOfSpecificTileInHand(tile.getImage()) < 1){
                throw new IllegalMove("You don't have enough of the "+image+" pieces to make this move");
            }
            if( !tempHexagon.getTiles().isEmpty()){
                throw new IllegalMove("There is already a piece on "+q+"|"+r);
            }
            if( board.getNeighbouringHexagonWithTiles(tempHexagon).isEmpty()){
                throw new IllegalMove("You can only play a piece next to one of your own pieces");
            }
            if( !board.isTopPieceOnHexagonOfPlayer(tempHexagon, getPlayer())){
                throw new IllegalMove("You played your piece next to an enemy piece");
            }
            if( !queenOnBoardAfterThreeTiles(image)) {
                throw new IllegalMove("It has been 3 turns, You have to play your queen bee piece!");
            } else {
                handlePlay(tempHexagon, tile);
            }
        }else {
            if(getPlayer().numberOfSpecificTileInHand(tile.getImage()) < 1){
                throw new IllegalMove("You don't have enough of the "+image+" pieces to make this move");
            }
            if( board.getHexagonByPosition(q, r).getTiles().size() != 0){
                throw new IllegalMove("There is already a piece on "+q+"|"+r);
            }
            if( board.getNeighbouringHexagonWithTiles(tempHexagon).isEmpty()) {
                throw new IllegalMove("You can only play a piece next to one of your own pieces!");
            } else {
                handlePlay(tempHexagon, tile);
            }
        }
    }

    /**
     * Handles the play() method if it can be played
//     * @param image Hive.Tile
//     * @param q Indication of position
//     * @param r Indication of position
     */
    private void handlePlay(HexagonClass hexagon, Piece tile){
        getPlayer().removeTileFromHand(tile.getImage());
        hexagon.addTileToHexagon(tile);
        nextTurn();
    }

    /**
     * Moves an already existing tile from position fromQ, fromR, to position toQ, toR
     * @param fromQ Indication of position to move from
     * @param fromR Indication of position to move from
     * @param toQ Indication of position to move to
     * @param toR Indication of position to move to
     * @throws IllegalMove if any condition results in false
     */
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove{
        if(board.getHexagonByPosition(fromQ, fromR).getTiles().isEmpty()){
            throw new IllegalMove("There is no piece on the given from position");
        }
        if(Arrays.asList(fromQ, fromR).equals(Arrays.asList(toQ, toR))){
            throw new IllegalMove("You can't move to your starting position");
        }

        HexagonClass fromHexagon = board.getHexagonByPosition(fromQ,fromR);
        HexagonClass toHexagon = board.getHexagonByPosition(toQ,toR);
        Piece tile = board.getHexagonByPosition(fromQ, fromR).getTiles().get(fromHexagon.getHeight()-1);

        if( !tile.getColour().equals(getPlayer().getColour())){
            throw new IllegalMove("The top tile on this hexagon isn't yours");
        }
        if( getPlayer().hasPlayedQB()) {

            fromHexagon.removeTopTile();

            if (board.getNeighbouringHexagonWithTiles(toHexagon).size() <= 0){
                fromHexagon.addTileToHexagon(tile);
                throw new IllegalMove("You selected a hexagon to move to that doesn't have any neighbours");
            }
            if( !board.isOneIsland(toHexagon, new ArrayList<>())){
                fromHexagon.addTileToHexagon(tile);
                throw new IllegalMove("By making this move, you break the island");
            }
            if( !tile.getValidMoves(fromHexagon).contains(toHexagon)) {
                fromHexagon.addTileToHexagon(tile);
                throw new IllegalMove(tile.getImage()+" can't be move to "+toQ+"-"+toR);
            }
            else {
                handleMove(toHexagon, tile);
            }
        }
        else {
            throw new IllegalMove("You haven't played you queen bee piece");
        }
    }

    /**
     * Method for handling the move itself
//     * @param tile Piece to move
//     * @param toQ Indication of position to move to
//     * @param toR Indication of position to move to
     */
    private void handleMove(HexagonClass toHexagon, Piece tile){
        toHexagon.addTileToHexagon(tile);
        nextTurn();
    }

    /**
     * Checks if any move is possible, if none, may pass turn
     * @throws IllegalMove if any move is possible
     */
    public void pass() throws IllegalMove{
        ArrayList<Piece>tiles = board.getTilesOfPlayer(getPlayer().getColour());
        for(Piece tile : tiles){
            if(tile.getValidMoves(board.getHexagonByTile(tile)).size() > 0){
                throw new IllegalMove("You still have moves. So, you can't pass");
            }
        }
        nextTurn();
    }

    /**
     * Switches the currentPlayer to the next player
     */
    public void nextTurn() {
        switch (currentPlayer) {
            case WHITE:
                currentPlayer = Hive.Player.BLACK;
                break;
            case BLACK:
                currentPlayer = Hive.Player.WHITE;
                break;
        }
    }

    /**
     * Checks if a queenBee has been surrounded
     * @return true if all positions neighbouring the queen bee contain a tile
     */
    public boolean isWinner(Player player){
        HexagonClass hexagonWithQB = null;
        switch (player) {
            case WHITE:
                hexagonWithQB = board.getQueenBee(board.getTilesOfPlayers().get(1));
                break;
            case BLACK:
                hexagonWithQB = board.getQueenBee(board.getTilesOfPlayers().get(0));
                break;
        }
        return board.getNeighbouringHexagonWithTiles(hexagonWithQB).size() == 6;
    }

    /**
     * Checks if both queens are surrounded
     * @return true if both queen bees are surrounded
     */
    public boolean isDraw(){
        HexagonClass hexagonWithWhiteQB = board.getQueenBee(board.getTilesOfPlayers().get(0));
        HexagonClass hexagonWithBlackQB = board.getQueenBee(board.getTilesOfPlayers().get(1));
        return board.getNeighbouringHexagonWithTiles(hexagonWithWhiteQB).size() == 6 &&
                board.getNeighbouringHexagonWithTiles(hexagonWithBlackQB).size() == 6;
    }

    /**
     * Checks rule that queen bee has to be on board within 4 turns (maximum after 3 tiles)
//     * @param image Hive.Player.QUEEN_BEE
     * @return true if said player has played a tile with image Hive.Player.QUEEN_BEE
     */
    private boolean queenOnBoardAfterThreeTiles(Hive.Tile image){
        if(image == Tile.QUEEN_BEE){
            return true;
        }
        if(getPlayer().getNumberOfPiecesInHand() <= 8 ){
            return getPlayer().hasPlayedQB();
        }
        return true;
    }
}