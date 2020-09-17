package animations;

import biuoop.KeyboardSensor;
import java.awt.Color;
import biuoop.DrawSurface;

/**
 * Game Over Screen animation.
 * @author Naveh Marchoom
 */
public class GameOverScreen implements Animation {

    private int score;

    /**
     * Creates a new GameOverScreen animation using the KeyboardSensor and the score given.
     * @param ks KeyboardSensor
     * @param scoreCounter Counter
     */
    public GameOverScreen(KeyboardSensor ks, int scoreCounter) {
        this.score = scoreCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface board, double dt) {
        board.setColor(Color.BLACK);
        board.fillRectangle(0, 0, board.getWidth(), board.getHeight());

        // Skull:
        board.setColor(Color.white);
        board.fillCircle(400, 230, 50);
        board.fillOval(340, 160, 120, 90);
        board.setColor(Color.black);
        board.drawOval(340, 160, 120, 90);
        // Teeth:
        for (int i = 340; i < 460; i += 10) {
            int a = Math.abs(6 - ((i - 340) / 10));
            board.drawLine(i, 210, i, 250 - 2 * a);
        }
        board.setColor(Color.WHITE);
        board.fillCircle(400, 150, 80);
        board.setColor(Color.black);
        board.drawCircle(400, 150, 80);
        board.setColor(Color.WHITE);
        board.fillCircle(400, 130, 90);
        board.setColor(Color.black);
        // Eyes:
        board.fillCircle(370, 150, 20);
        board.fillCircle(430, 150, 20);
        board.fillCircle(370, 155, 18);
        board.fillCircle(430, 155, 18);
        // Nose:
        board.fillCircle(400, 190, 10);
        board.fillCircle(400, 197, 13);

        board.setColor(Color.WHITE);
        String loseMessage = "Game Over. your score is";
        board.drawText(board.getWidth() / 2 - loseMessage.length() * 12, 400, loseMessage, 50);
        board.drawText(board.getWidth() / 2 - (28 * (int) Math.floor(Math.log10(this.score))),
                480, "" + this.score, 80);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

}
