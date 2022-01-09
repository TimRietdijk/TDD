package nl.hanze.hive;

public class PieceFactory {

    Piece makePiece(Hive.Tile tile){
        switch (tile){
            case QUEEN_BEE:
                return new QueenBeeClass();
            case SPIDER:
                return new SpiderClass();
            case BEETLE:
                return new BeetleClass();
            case SOLDIER_ANT:
                return new SoldierAntClass();
            case GRASSHOPPER:
                return new GrasshopperClass();
        }
        return null;
    }

}
