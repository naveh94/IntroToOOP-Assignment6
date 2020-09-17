package sprites;
import java.awt.Color;

import animations.Effects;
import biuoop.DrawSurface;
import gameobjects.Sprite;
/**
 * Rectangle Sprite Object.
 * @author Naveh Marchoom
 */
public class RectangleSprite implements Sprite {

    private Color c;
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Creates a new rectangle sprite using top left corner, width, height and color.
     * @param x int
     * @param y int
     * @param width int
     * @param height int
     * @param color Color
     */
    public RectangleSprite(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.c = color;
    }

    @Override
    public void drawOn(DrawSurface board) {
        board.setColor(c);
        board.fillRectangle(x, y, width, height);
    }

    @Override
    public void timePassed(double dt) {
        // Not a dynamic sprite.
    }

    /**
     * Creates a new RectangleSprite from given String.
     * @param s String
     * @return RectangleSprite
     */
    public static RectangleSprite fromString(String s) {
        return fromString(s, 0, 0, 800, 600);
    }

    /**
     * Creates a new RectangleSprite from given String and parameters.
     * @param s String
     * @param x int
     * @param y int
     * @param width int
     * @param height int
     * @return RectangleSprite
     */
    public static RectangleSprite fromString(String s, int x, int y, int width, int height) {
        if (s.startsWith("RGB")) {
            String[] rgb = s.substring(4).split(",");
            return new RectangleSprite(x, y, width, height, new Color(Integer.parseInt(rgb[0]),
                    Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
        } else {
            return new RectangleSprite(x, y, width, height, Effects.colorFromString(s));
        }
    }
}