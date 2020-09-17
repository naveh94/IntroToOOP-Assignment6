package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import gameobjects.Sprite;
import gameobjects.SpriteCollection;
import sprites.ImageSprite;
import sprites.RectangleSprite;
/**
 * Background sprite object.
 * @author Naveh Marchoom
 */
public class Background implements Sprite {

    private SpriteCollection sprites;

    /**
     * Creates a new Background from string.
     * @param s String
     */
    public void fromString(String s) {
        if (s.startsWith("image")) {
            Image i = null;
            InputStream is = null;
            try {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                        s.substring(6).replace(")", ""));
                i = ImageIO.read(is);
                this.sprites.addSprite(new ImageSprite(0, 0, i));
            } catch (IOException e) {
                System.out.println("Error reading image.");
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        System.out.println("Failed closing image '"
                                + s.substring(6).replace(")", "") + "'.");
                        e.printStackTrace();
                    }
                }
            }
        } else if (s.startsWith("color(RBG")) {
            String[] rbg = s.substring(10).replace(")", "").split(",");
            this.sprites.addSprite(new RectangleSprite(0, 0, 800, 600,
                    new Color(Integer.parseInt(rbg[0]),
                            Integer.parseInt(rbg[1]), Integer.parseInt(rbg[2]))));
        } else if (s.startsWith("color(")) {
            String color = s.substring(6).replace(")", "");
            this.sprites.addSprite(new RectangleSprite(0, 0, 800, 600, Color.getColor(color)));
        }
    }

    @Override
    public void drawOn(DrawSurface board) {
        sprites.drawOnAll(board);
    }

    @Override
    public void timePassed(double dt) {
        // Not a dynamic background.
    }

}
