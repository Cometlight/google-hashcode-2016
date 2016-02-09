import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Zopo on 09.02.2016.
 */
public class Binary2dField {
    private PriorityQueue<Move> _moves;
    private boolean[][] _tiles;

    /*
     * CONSTRUCTOR
     */

    /*
     * METHODS
     */

    public void optimizeMoves(){
        //TODO
    }

    /*
     * GETTER AND SETTER
     */

    public PriorityQueue<Move> getMoves() {
        return _moves;
    }

    public void setMoves(PriorityQueue<Move> moves) {
        _moves = moves;
    }

    public boolean[][] getTiles() {
        return _tiles;
    }

    public void setTiles(boolean[][] tiles) {
        _tiles = tiles;
    }
}
