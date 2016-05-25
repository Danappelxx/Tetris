/**
 * Created by dan on 5/10/16.
 */

import Util.Board;

/**
  * Middle ground between View and Model
  * - Controller creates ViewModel from Model
  * - ViewModel gets the information from Model that View will display
  * - Controller passes ViewModel to View, View updates itself from it
 */
public class TetrisViewModel {

    private Board board;
    private int score;

    public TetrisViewModel(TetrisModel model) {
        // copy the board so that the ViewModel's modifications don't show up everywhere
        board = model.getBoardCopy();
        score = model.getRowsCleared() * 100;

        // ensure that there are no null pointer exceptions when drawing
        board.fillBlank();

        // stamp the shape onto the board
        model.getShadow().addToBoard(board);
        model.getShapeInMotion().addToBoard(board);
    }

    public Board getBoard() {
        return board;
    }
    public int getScore() { return score; }
}
