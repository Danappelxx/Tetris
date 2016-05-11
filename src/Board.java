import java.util.function.Consumer;

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

    public Tile[][] getTiles() {
        return tiles;
    }


    /*
        Takes the shape's tiles and adds them to the Board.
     */
    //TODO: Move this somewhere better
    public void addShapeInMotion(ShapeInMotion shapeInMotion) {
        if (shapeInMotion == null) { return; }

        Shape shape = shapeInMotion.getShape();
        int shapeX = shapeInMotion.getX();
        int shapeY = shapeInMotion.getY();

        for (int row = 0; row < Shape.NUM_ROWS; row++) {
            for (int col = 0; col < Shape.NUM_COLS; col++) {
                if (shape.isATile(row, col)) {
                    Tile tile = shape.getTile(row, col);
                    tiles[row + shapeY][col + shapeX] = tile;
                }
            }
        }
    }
}
