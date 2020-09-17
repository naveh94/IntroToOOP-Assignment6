package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import gameobjects.Sprite;

/**
 * Level: Wide Easy's Background Sprite.
 * @author Naveh Marchoom
 */
public class WideEasyBG implements Sprite {

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface board) {
        board.setColor(Color.WHITE);
        board.fillRectangle(0, 0, board.getWidth(), board.getHeight());

        board.setColor(Color.BLACK);
        board.fillRectangle(0, 261, board.getWidth(), board.getHeight() - 260);

        board.setColor(new Color(239, 231, 176));
        board.fillCircle(200, 150, 60);

        for (int i = 0; i < 100; i++) {
            board.drawLine(200, 150, i * (board.getWidth()) / 100, 260);
        }

        board.setColor(new Color(235, 215, 73));
        board.fillCircle(200, 150, 50);
        board.setColor(new Color(255, 225, 24));
        board.fillCircle(200, 150, 40);

        for (int i = 0; i < 800; i += 10) {
            double freq = 0.006;
            int red = (int) (Math.sin(freq * i + 0) * 127 + 128);
            int green = (int) (Math.sin(freq * i + 2) * 127 + 128);
            int blue = (int) (Math.sin(freq * i + 4) * 127 + 128);
            board.setColor(new Color(red, green, blue));
            board.drawLine(i, 261, board.getWidth() / 2 + 200, board.getHeight());
        }

        board.setColor(Color.decode("#0f0f0f"));
        board.fillCircle(board.getWidth() / 2 + 200, board.getHeight(), 60);
        board.setColor(Color.black);
        board.fillCircle(board.getWidth() / 2 + 200, board.getHeight(), 50);
        board.setColor(Color.decode("#2b2b2b"));
        board.fillCircle(board.getWidth() / 2 + 200, board.getHeight(), 40);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed(double dt) {
        // Nothing to change.
    }
}
