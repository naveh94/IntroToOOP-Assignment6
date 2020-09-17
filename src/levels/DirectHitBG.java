package levels;
import biuoop.DrawSurface;
import gameobjects.Sprite;

import java.awt.Color;

/**
 * Level: Direct Hit's Background Sprite.
 * @author Naveh Marchoom
 *
 */
public class DirectHitBG implements Sprite {

    private int framesFromStart = 0;
    private double secondsFromStart = 0.0;
    private int crossHeirX = 400;
    private int crossHeirY = 166;

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface board) {
        board.setColor(Color.black);
        board.fillRectangle(0, 0, board.getWidth(), board.getHeight());

        // Crossheir :
        board.setColor(Color.blue);

        if (this.secondsFromStart > 0.0 && this.secondsFromStart < 0.5) {
            this.crossHeirX += 10;
        }
        if (this.secondsFromStart > 0.5 && this.secondsFromStart < 1.0) {
            this.crossHeirX -= 15;
        }
        if (this.secondsFromStart > 1.0 && this.secondsFromStart < 1.5) {
            this.crossHeirX += 6;
        }
        if (this.secondsFromStart > 1.5 && this.secondsFromStart < 2.0) {
            this.crossHeirX -= 1;
        }

        board.drawCircle(crossHeirX, crossHeirY, 70);
        board.drawCircle(crossHeirX, crossHeirY, 100);
        board.drawCircle(crossHeirX, crossHeirY, 130);
        board.drawLine(crossHeirX, crossHeirY - 166, crossHeirX, crossHeirY - 36);
        board.drawLine(crossHeirX, crossHeirY + 36, crossHeirX, crossHeirY + 166);
        board.drawLine(crossHeirX - 166, crossHeirY, crossHeirX - 36, crossHeirY);
        board.drawLine(crossHeirX + 36, crossHeirY, crossHeirX + 166, crossHeirY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed(double dt) {
        this.framesFromStart++;
        this.secondsFromStart = ((double) this.framesFromStart) * dt;
    }

}
