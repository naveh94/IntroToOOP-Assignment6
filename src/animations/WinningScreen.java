package animations;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * Winning Screen Animation.
 * @author Naveh Marchoom
 */
public class WinningScreen implements Animation {

    private int score;

    /**
     * Create a new WinningScreen Animation using the keyboardSensor and the score given.
     * @param scoreCounter Counter
     */
    public WinningScreen(int scoreCounter) {
        this.score = scoreCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface board, double dt) {

        // Sky:
        board.setColor(Color.decode("#00b4ff"));
        board.fillRectangle(0, 0, board.getWidth(), board.getHeight());

        // Rainbow:
        for (int i = 0; i < 32; i++) {
            int red = (int) (Math.sin(0.31 * i + 0) * 127 + 128);
            int green = (int) (Math.sin(0.31 * i + 2) * 127 + 128);
            int blue = (int) (Math.sin(0.31 * i + 4) * 127 + 128);
            board.setColor(new Color(red, green, blue));
            board.fillCircle(400, 250 + i * 2, 200);
        }

        // Sky:
        board.setColor(Color.decode("#00b4ff"));
        board.fillCircle(400, 250 + 32 * 2, 200);
        board.fillRectangle(0, 240, board.getWidth(), 100);

        // Clouds:
        board.setColor(Color.white);
        board.fillCircle(200, 230, 30);
        board.fillCircle(250, 220, 40);
        board.fillCircle(250, 260, 30);
        board.fillCircle(300, 250, 50);
        board.fillCircle(300, 200, 30);
        board.fillCircle(360, 200, 60);
        board.fillCircle(360, 270, 60);
        board.fillCircle(440, 270, 60);
        board.fillCircle(440, 200, 60);
        board.fillCircle(500, 250, 50);
        board.fillCircle(500, 200, 30);
        board.fillCircle(550, 220, 40);
        board.fillCircle(550, 260, 30);
        board.fillCircle(600, 230, 30);

        // Smily:
        board.setColor(Color.BLACK);
        board.fillCircle(402, 252, 100);
        board.setColor(Color.yellow);
        board.fillCircle(400, 250, 100);
        board.setColor(Color.black);
        board.fillCircle(400, 260, 60);
        board.setColor(Color.yellow);
        board.fillCircle(400, 250, 60);
        board.setColor(Color.BLACK);
        board.fillCircle(430, 230, 10);
        board.fillCircle(370, 230, 10);

        // Text:
        board.setColor(Color.BLACK);
        String winMessage = "You Win! your score is";
        board.drawText(board.getWidth() / 2 - winMessage.length() * 12, 400, winMessage, 50);
        board.drawText(board.getWidth() / 2 - (28 * (int) Math.floor(Math.log10(this.score))),
                480, "" + this.score, 80);
        board.setColor(Color.YELLOW);
        board.drawText((board.getWidth() / 2 - winMessage.length() * 12) - 2, 400 - 2, winMessage, 50);
        board.drawText((board.getWidth() / 2 - (28 * (int) Math.floor(Math.log10(this.score)))) - 2,
                480 - 2, "" + this.score, 80);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
