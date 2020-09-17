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
 * Level: Green3's LevelInformation.
 * @author Naveh Marchoom
 */
public class Green3 implements LevelInformation {

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l =  new java.util.ArrayList<>();
        l.add(Velocity.fromAngleAndSpeed(Velocity.straightUpAngle() - 20, 6 * 60));
        l.add(Velocity.fromAngleAndSpeed(Velocity.straightUpAngle() + 20, 6 * 60));
        return l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleSpeed() {
        return 8 * 60;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleWidth() {
        return 85;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String levelName() {
        return "Green 3";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sprite getBackground() {
        return new Green3BG();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Block> blocks() {
        List<Block> l = new java.util.ArrayList<>();
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < 5; i++) {
            java.awt.Color c = new java.awt.Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            for (int j = i; j < 10; j++) {
                Block b = new Block(new Point(775 - (50 * (10 - j)), 150 + (i * 25)), 50, 25);
                b.setFill(new DrawColor(c));
                b.setStroke(new DrawStroke(Color.black));
                if (i == 0) {
                    b.setHitPoints(2);
                } else {
                    b.setHitPoints(1);
                }
                b.showHp(true);
                l.add(b);
            }
        }
        return l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
