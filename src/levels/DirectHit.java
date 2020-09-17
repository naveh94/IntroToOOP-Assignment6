package levels;
import java.awt.Color;
import java.util.List;

import blockcreator.DrawColor;
import blockcreator.DrawStroke;
import gameobjects.Block;
import gameobjects.Sprite;
import geometry.Point;
import geometry.Velocity;

/**
 * Level: Direct Hit's LevelInformation.
 * @author Naveh Marchoom
 */
public class DirectHit implements LevelInformation {

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new java.util.ArrayList<Velocity>();
        l.add(Velocity.fromAngleAndSpeed(Velocity.straightUpAngle(), 60));
        return l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleSpeed() {
        return 5 * 60;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleWidth() {
        return 86;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sprite getBackground() {
        DirectHitBG b = new DirectHitBG();
        return b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Block> blocks() {
        Block b = new Block(new Point(385, 150), 32, 32);
        b.setFill(new DrawColor(Color.RED));
        b.setStroke(new DrawStroke(Color.BLACK));
        b.setHitPoints(1);
        List<Block> l = new java.util.ArrayList<Block>();
        l.add(b);
        return l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
