package Util;

import java.util.function.Consumer;

/**
 * Created by dan on 5/10/16.
 */

public class Board {
    public final int numRows;
    public final int numCols;

    protected Tile[][] tiles;

    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.tiles = new Tile[numRows][numCols];
    }

    public Board(Board from) {
        this(from.numRows, from.numCols);
        for (int row = 0; row < numRows; row++)
            for (int col = 0; col < numCols; col++)
                if (from.getTiles()[row][col] != null)
                    tiles[row][col] = from.getTiles()[row][col].copy();
                else
                    tiles[row][col] = null;
    }

    public void forEachTile(Consumer<Tile> f) {
        for (Tile[] row: tiles)
            for (Tile tile: row)
                f.accept(tile);
    }

    public void fillBlank() {
        for (int row = 0; row < numRows; row++)
            for (int col = 0; col < numCols; col++)
                if (tiles[row][col] == null)
                    tiles[row][col] = new Tile();
    }

    public void removeRow(int row) {

        // remove every tile in the row
        for (int x = 0; x < numCols; x++) {
            tiles[row][x] = null;
        }

        // shift down every row above it
        for (int y = row - 1; y >= 0; y--)
            tiles[y+1] = tiles[y];

        // remove topmost row
        tiles[0] = new Tile[numCols];
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                result += tiles[y][x] == null ? "-" : "x";
            }
            result += "\n";
        }
        return result;
    }
    // MARK: Getters & Setters
    public Tile[][] getTiles() {
        return tiles;
    }
    void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getWidth() {
        return tiles[0].length;
    }
    public int getHeight() {
        return tiles.length;
    }
}
