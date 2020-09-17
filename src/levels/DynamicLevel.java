package levels;

import java.util.List;

import gameobjects.Block;
import gameobjects.Sprite;
import geometry.Velocity;

/**
 * Dynamic level object. Can be changed after construction.
 * @author Naveh Marchoom
 */
public class DynamicLevel implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> initialBallVelo;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numOfBlocks;

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Set amount of balls.
     * @param num int
     */
    public void setNumberOfBalls(int num) {
        this.numberOfBalls = num;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelo;
    }

    /**
     * Set balls' velocities.
     * @param l List<Velocity>
     */
    public void setInitialBallVelocities(List<Velocity> l) {
        this.initialBallVelo = l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Set the paddle's speed.
     * @param speed int
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Set the paddle's width.
     * @param width int
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Set the level's name.
     * @param name String
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Set the level's background.
     * @param bg Sprite
     */
    public void setBackground(Sprite bg) {
        this.background = bg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Set the level's blocks.
     * @param l List<Block>
     */
    public void setBlocks(List<Block> l) {
        this.blocks = l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }

    /**
     * Set the blocks to remove number.
     * @param num int
     */
    public void setNumberOfBlocksToRemove(int num) {
        this.numOfBlocks = num;
    }
}
