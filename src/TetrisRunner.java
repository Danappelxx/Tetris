/**
 * Created by dan on 5/9/16.
 */

public class TetrisRunner {
    public static void main(String[] args) {
        TetrisView view = new TetrisView();
        TetrisModel model = new TetrisModel();
        TetrisController controller = new TetrisController(view, model);

        view.setVisible(true);
    }
}
