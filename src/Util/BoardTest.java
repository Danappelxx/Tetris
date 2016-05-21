package Util;

import junit.framework.TestCase;

/**
 * Created by dan on 5/20/16.
 */

public class BoardTest extends TestCase {

    Board board = new Board(4, 3);

    Tile none = null;
    Tile some = new Tile();

    public void setUp() throws Exception {
        super.setUp();

        board.setTiles(new Tile[][]{
                {none, some, none},
                {none, none, none},
                {some, some, none},
                {some, none, some}
        });
    }

    public void testRemoveRow() throws Exception {

        String original = board.toString();

        assertEquals(original,
                "-x-\n" +
                "---\n" +
                "xx-\n" +
                "x-x\n"
        );

        board.removeRow(2);

        String removed = board.toString();

        String expected =
                "---\n" +
                "-x-\n" +
                "---\n" +
                "x-x\n";

        assertEquals(expected, removed);
    }

    public void testToString() throws Exception {
        assertEquals(board.toString(),
                "-x-\n" +
                "---\n" +
                "xx-\n" +
                "x-x\n"
        );
    }
}