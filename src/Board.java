import java.util.function.Consumer;
import Shapes.Shape;
import Util.Tile;

/**
 * Created by dan on 5/10/16.
 */

public class Board {
    public static final int NUM_COLS = 10;
    public static final int NUM_ROWS = 20;

    private Tile[][] tiles = new Tile[NUM_ROWS][NUM_COLS];

    public Board() {
    }

    public Board(Board from) {
        for (int row = 0; row < NUM_ROWS; row++)
            for (int col = 0; col < NUM_COLS; col++)
                tiles[row][col] = from.getTiles()[row][col];
    }

    public void forEachTile(Consumer<Tile> f) {
        for (Tile[] row: tiles)
            for (Tile tile: row)
                f.accept(tile);
    }

    public void fillBlank() {
        for (int row = 0; row < NUM_ROWS; row++)
            for (int col = 0; col < NUM_COLS; col++)
                if (tiles[row][col] == null)
                    tiles[row][col] = new Tile();

    }

    // MARK: Getters & Setters
    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return tiles[0].length;
    }
    public int getHeight() {
        return tiles.length;
    }
}
