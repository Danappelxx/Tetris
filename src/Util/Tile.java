package Util;

import java.awt.*;

/**
 * Created by dan on 5/9/16.
 */
public class Tile {
    private Color color;

    public Tile() {}

    public Tile copy() {
        Tile tile = new Tile();
        tile.color = color;
        return tile;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
