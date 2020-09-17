package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import gameobjects.Sprite;

/**
 * Level: Final Four's Background Sprite.
 * @author Naveh Marchoom
 *
 */
public class FinalFourBG implements Sprite {

    private int framesFromStart = 0;
    private double secondsFromStart = 0.0;
    private int rainRate;

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface board) {

        // Sky:
        board.setColor(Color.decode("#3d4279"));
        board.fillRectangle(0, 0, board.getWidth(), board.getHeight());

        // Lightning:
        if (this.secondsFromStart > 10 && this.secondsFromStart < 10.5) {
            board.setColor(Color.decode("#7d87f4"));
            board.fillRectangle(0, 0, board.getWidth(), board.getHeight());

            board.setColor(Color.WHITE);
            board.drawLine(100, 20, 200, 200);
            if (this.secondsFromStart > 10.2) {
                board.drawLine(200, 200, 100, 500);
                board.drawLine(200, 200, 400, 300);
                if (this.secondsFromStart > 10.3) {
                    board.drawLine(400, 300, 400, 500);
                    board.drawLine(400, 300, 700, 600);
                }
            }
        }

        // Rain:
        if (this.secondsFromStart > 0) {
            board.setColor(Color.decode("#929292"));
            java.util.Random rand = new java.util.Random();
            rainRate = rand.nextInt(100);
            for (int i = 1; i < 50; i++) {
                board.drawLine(i * (i % 30) + rainRate, 20, i * (i % 30) - 100 + rainRate, board.getHeight());
            }
        }

        // Clouds:
        board.setColor(Color.decode("#7c7c7c"));
        board.fillCircle(580, 40, 30);
        board.fillCircle(380, 40, 30);
        board.fillCircle(280, 40, 30);
        board.fillCircle(180, 40, 30);
        board.fillCircle(80, 40, 30);
        board.fillCircle(690, 30, 70);
        board.fillCircle(790, 30, 40);
        board.fillCircle(90, 30, 30);
        board.fillCircle(490, 30, 40);
        board.fillCircle(590, 30, 30);
        board.fillCircle(390, 30, 50);
        board.fillCircle(190, 30, 60);
        board.fillCircle(90, 30, 30);
        board.setColor(Color.decode("#a7a7a7"));
        board.fillCircle(760, 20, 40);
        board.fillCircle(630, 60, 30);
        board.fillCircle(750, 40, 40);
        board.fillCircle(660, 10, 40);
        board.fillCircle(260, 30, 40);
        board.fillCircle(460, 20, 40);
        board.fillCircle(560, 30, 40);
        board.fillCircle(460, 60, 40);
        board.fillCircle(430, 10, 30);
        board.fillCircle(360, 20, 40);
        board.fillCircle(260, 20, 40);
        board.fillCircle(230, 10, 30);
        board.fillCircle(160, 20, 40);
        board.fillCircle(30, 10, 30);
        board.fillCircle(60, 20, 40);
        board.fillCircle(530, 10, 30);
        board.fillCircle(300, 40, 40);
        board.fillCircle(100, 40, 40);
        board.fillCircle(10, 40, 40);
        board.fillCircle(500, 40, 40);
        board.setColor(Color.decode("#cecece"));
        board.fillCircle(600, 35, 30);
        board.fillCircle(730, 20, 30);
        board.fillCircle(800, 30, 30);
        board.fillCircle(440, 46, 30);
        board.fillCircle(510, 40, 30);
        board.fillCircle(400, 40, 30);
        board.fillCircle(300, 40, 30);
        board.fillCircle(100, 40, 30);
        board.fillCircle(0, 40, 30);
        board.fillCircle(480, 20, 30);
        board.fillCircle(280, 20, 30);
        board.fillCircle(180, 20, 30);
        board.fillCircle(80, 20, 30);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed(double dt) {
        this.framesFromStart++;
        this.secondsFromStart = this.framesFromStart * dt;
        if (this.secondsFromStart == 20) {
            this.framesFromStart = 0;
        }
    }
}
