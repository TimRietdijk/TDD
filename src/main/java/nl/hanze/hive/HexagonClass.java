package nl.hanze.hive;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class HexagonClass {
    private int q;
    private int r;
    private ArrayList<Piece> Tiles = new ArrayList<>();

    HexagonClass(int q, int r){ this.q = q; this.r = r; }
    List<Integer> getPosition(){ return Arrays.asList(q, r); }
    void addTileToHexagon(Piece tile){ Tiles.add(tile); }
    ArrayList<Piece> getTiles(){ return Tiles; }
    void removeTopTile() { Tiles.remove(Tiles.size()-1); }
    int getHeight(){ return Tiles.size(); }
    int getQ(){return q;}
    int getR(){return r;}
}
