import javax.swing.*;
import java.awt.event.*;

/**
 * Created by dan on 5/10/16.
 */

public class TetrisController {

    public static class GameOverException extends Exception {}

    public static final int TICK_INTERVAL_MS = 500;

    TetrisView view;
    TetrisModel model;
    TetrisViewModel viewModel;

    Timer timer;
    KeyListener keyListener;

    public TetrisController(TetrisView view, TetrisModel model) {
        this.view = view;
        this.model = model;

        setupKeyListeners();
        setupTimer();
        tick(true);
    }

    private void setupKeyListeners() {
        keyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    // LEFT
                    case 37:
                        model.shiftLeftIfPossible();
                        break;
                    // RIGHT
                    case 39:
                        model.shiftRightIfPossible();
                        break;
                    // UP
                    case 38:
                        model.rotateIfPossible();
                        break;
                    // DOWN
                    case 40:
                        tick(true);
                        return;
                    // SPACE
                    case 32:
                        model.drop();
                        tick(true);
                        return;
                    default:
                        return;
                }
                tick(false);
            }
        };
        view.addKeyListener(keyListener);
    }

    private void setupTimer() {
        timer = new Timer(TICK_INTERVAL_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick(true);
            }
        });
        timer.start();
    }

    private void tick(boolean shouldShiftDown) {

        if (shouldShiftDown) model.shiftDownIfPossible();

        model.clearRows();

        try {
            model.checkGameOver();
        } catch (GameOverException e) {
            gameOver();
            return;
        }

        model.createShapeInMotionIfNecessary();

        viewModel = new TetrisViewModel(model);
        view.configure(viewModel);

        view.repaint();
    }

    private void gameOver() {
        timer.stop();
        view.removeKeyListener(keyListener);

        view.displayGameOverDialog(viewModel.getScore());
    }
}
