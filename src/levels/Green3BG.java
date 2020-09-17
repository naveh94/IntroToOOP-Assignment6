package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import gameobjects.Sprite;

/**
 * Level: Green3's Background Sprite.
 * @author Naveh Marchoom
 */
public class Green3BG implements Sprite {

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface board) {
        board.setColor(Color.GREEN.darker().darker());
        board.fillRectangle(0, 0, board.getWidth(), board.getHeight());

        // Sky:
        board.setColor(Color.BLUE.darker().darker());
        for (int i = -50; i <= 50; i++) {
            board.drawLine(i, 0, 0, 490);
        }
        for (int i = 20; i <= 120; i++) {
            board.drawLine(i, 0, 70, 450);
        }
        for (int i = 50; i <= 150; i++) {
            board.drawLine(i, 0, 100, 400);
        }
        for (int i = 100; i <= 200; i++) {
            board.drawLine(i, 0, 150, 370);
        }
        for (int i = 150; i <= 250; i++) {
            board.drawLine(i, 0, 200, 330);
        }
        for (int i = 200; i <= 300; i++) {
            board.drawLine(i, 0, 250, 270);
        }
        for (int i = 270; i <= 370; i++) {
            board.drawLine(i, 0, 320, 230);
        }
        for (int i = 300; i <= 400; i++) {
            board.drawLine(i, 0, 350, 170);
        }
        for (int i = 380; i <= 480; i++) {
            board.drawLine(i, 0, 430, 130);
        }
        for (int i = 400; i <= 500; i++) {
            board.drawLine(i, 0, 450, 120);
        }
        for (int i = 450; i <= 550; i++) {
            board.drawLine(i, 0, 500, 100);
        }
        for (int i = 500; i <= 600; i++) {
            board.drawLine(i, 0, 550, 150);
        }
        for (int i = 580; i <= 680; i++) {
            board.drawLine(i, 0, 630, 200);
        }
        for (int i = 620; i <= 720; i++) {
            board.drawLine(i, 0, 670, 250);
        }
        for (int i = 700; i <= 800; i++) {
            board.drawLine(i, 0, 750, 300);
        }
        for (int i = 720; i <= 820; i++) {
            board.drawLine(i, 0, 770, 350);
        }

        // Moon:
        board.setColor(Color.WHITE);
        board.fillCircle(670, 50, 30);

        // Roof:
        board.setColor(Color.red.darker().darker());
        for (int i = 150; i <= 250; i++) {
            board.drawLine(i, 300, 200, 155);
        }
        board.setColor(Color.BLACK);
        board.drawLine(150, 300, 200, 155);
        board.drawLine(250, 300, 200, 155);

        // Castle:
        board.setColor(Color.gray.darker());
        board.fillRectangle(150, 300, 100, 300);
        board.setColor(Color.BLACK);
        board.drawRectangle(150, 300, 100, 300);
        board.setColor(Color.gray.darker().darker());
        board.fillRectangle(140, 300, 120, 70);
        board.setColor(Color.BLACK);
        board.drawRectangle(140, 300, 120, 70);
        board.setColor(Color.gray.darker());
        board.fillRectangle(130, 300, 140, 50);
        board.setColor(Color.BLACK);
        board.drawRectangle(130, 300, 140, 50);
        board.setColor(Color.gray.darker());
        board.fillRectangle(130, 287, 23, 30);
        board.fillRectangle(188, 287, 24, 30);
        board.fillRectangle(247, 287, 23, 30);
        board.setColor(Color.BLACK);
        board.drawRectangle(130, 287, 23, 30);
        board.drawRectangle(188, 287, 24, 30);
        board.drawRectangle(247, 287, 23, 30);
        board.setColor(Color.gray.darker());
        board.fillRectangle(131, 301, 139, 30);

        // Windows:
        board.setColor(Color.BLACK);
        board.fillRectangle(190, 400, 20, 25);
        board.fillCircle(200, 402, 10);
        board.fillRectangle(190, 470, 20, 25);
        board.fillCircle(200, 472, 10);
        board.fillRectangle(190, 540, 20, 25);
        board.fillCircle(200, 542, 10);

        // Bricks
        board.drawRectangle(143, 300, 24, 13);
        board.drawRectangle(158, 313, 26, 13);
        board.drawLine(184, 326, 210, 326);
        board.drawRectangle(173, 326, 26, 11);
        board.drawLine(184, 337, 184, 349);
        board.drawRectangle(187, 425, 26, 10);
        board.drawLine(200, 435, 200, 443);
        board.drawLine(200, 443, 220, 443);
        board.drawRectangle(187, 495, 26, 10);
        board.drawRectangle(187, 565, 26, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed(double dt) {
        // Not a dynamic Background.
    }

}
