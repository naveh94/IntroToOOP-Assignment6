package blockcreator;

import java.util.Map;

import gameobjects.Block;
import geometry.Point;

/**
 * CreateBlock object implementing BlockCreator.
 * Saves given parameters for Block and creats blocks with those parameters
 * in given position.
 * @author Naveh Marchoom
 *
 */
public class CreateBlock implements BlockCreator {

    private int width;
    private int height;
    private int hp;
    private Drawer fill;
    private Drawer stroke;
    private Map<Integer, Drawer> fillK;

    /**
     * Creates a new CreateBlock object.
     * @param bWidth int
     * @param bHeight int
     * @param hitPoints int
     * @param bFill Drawer
     * @param bStroke Drawer
     * @param bFillK Map<Integer, Drawer>
     */
    public CreateBlock(int bWidth, int bHeight, int hitPoints, Drawer bFill,
            Drawer bStroke, Map<Integer, Drawer> bFillK) {
        this.width = bWidth;
        this.height = bHeight;
        this.hp = hitPoints;
        this.fill = bFill;
        this.stroke = bStroke;
        this.fillK = bFillK;
    }

    @Override
    public Block create(int xPos, int yPos) {
        Block b = new Block(new Point(xPos, yPos), this.width, this.height);
        b.setFill(this.fill);
        b.setStroke(this.stroke);
        b.setFillK(this.fillK);
        b.setHitPoints(this.hp);
        return b;
    }
}
