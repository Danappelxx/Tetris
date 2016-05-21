import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by dan on 5/10/16.
 */

public class TetrisController {

    public static class GameOverException extends Exception {}

    public static final int TICK_INTERVAL_MS = 500;

    TetrisView view;
    TetrisModel model;

    Timer timer;

    public TetrisController(TetrisView view, TetrisModel model) {
        this.view = view;
        this.model = model;

        setupKeyListeners();
        setupTimer();
        tick(true);
    }

    private void setupKeyListeners() {
        view.addKeyListener(new KeyAdapter() {
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
                    default:
                        return;
                }
                tick(false);
            }
        });
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
            model.createShapeInMotionIfNecessary();
        } catch (GameOverException e) {
            gameOver();
            return;
        }

        TetrisViewModel viewModel = new TetrisViewModel(model);
        view.configure(viewModel);

        view.repaint();
    }

    private void gameOver() {
        timer.stop();

        // TODO: Replace this with a popup or something
        System.out.println("Game over!");
    }
}
