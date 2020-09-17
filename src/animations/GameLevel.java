package animations;

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import blockcreator.DrawColor;
import gameobjects.AnimationRunner;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Collidable;
import gameobjects.Counter;
import gameobjects.DeathZone;
import gameobjects.GameEnvironment;
import gameobjects.Paddle;
import gameobjects.Sprite;
import gameobjects.SpriteCollection;
import geometry.Point;
import geometry.Velocity;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.ScoreTrackingListener;
import indicators.BlocksLeftIndicator;
import indicators.LivesIndicator;
import indicators.NameIndicator;
import indicators.ScoreIndicator;
import levels.LevelInformation;

/**
 * A collection of all the sprites and collidable on the current game.
 * @author Naveh Marchoom
 */
public class GameLevel implements Animation {

    // Game Objects Collection:
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private LevelInformation level;

    // Mechanics:
    private GUI gui;
    private AnimationRunner runner;
    private KeyboardSensor ks;

    // Counters:
    private Counter remainingBlocks = new Counter(0);
    private Counter remainingBalls = new Counter(0);
    private Counter score;
    private Counter lives;

    /**
     * Create a new GameLevel object and set the AnimationRunner,
     * the GUI and the keyboard sensor.
     * @param runner AnimationRunner
     * @param gui GUI
     * @param ks KeyboardSensor
     */
    public GameLevel(AnimationRunner runner, GUI gui, KeyboardSensor ks) {
        this.runner = runner;
        this.gui = gui;
        this.ks = ks;
    }

    /**
     * Initialize a new level, setting the score and the lives counters, and creating the level
     * according the the LevelInfo given.
     * @param width The GUI width.
     * @param height The GUI height.
     * @param levelInfo LevelInformation
     * @param scoreCounter Counter
     * @param livesCounter Counter
     */
    public void initialize(int width, int height, LevelInformation levelInfo, Counter scoreCounter,
            Counter livesCounter) {

        // Set the level:
        this.level = levelInfo;

        // Add the background to the sprites:
        this.sprites.addSprite(this.level.getBackground());

        // Set the lives and score Counters:
        this.lives = livesCounter;
        this.score = scoreCounter;

        // Set the Block counter:
        this.remainingBlocks.increase(this.level.numberOfBlocksToRemove());

        // Create the Board borders borders.
        this.createBorders(width, height);

        // Create the Death Zone:
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        DeathZone dz = new DeathZone(new Point(-1, height), width + 2, 10);
        dz.addHitListener(ballRemover);
        dz.addToGame(this);

        // Set the block remover and score tracker:
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener sTL = new ScoreTrackingListener(this.score);

        // Add the blocks:
        for (Block b : this.level.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(sTL);
            b.addToGame(this);
        }

        // Create the indicators:
        LivesIndicator livesPanel = new LivesIndicator(this.lives);
        this.sprites.addSprite(livesPanel);
        ScoreIndicator scorePanel = new ScoreIndicator(this.score);
        this.sprites.addSprite(scorePanel);
        NameIndicator namePanel = new NameIndicator(this.level.levelName());
        this.sprites.addSprite(namePanel);
        BlocksLeftIndicator blocksPanel = new BlocksLeftIndicator(this.remainingBlocks);
        this.sprites.addSprite(blocksPanel);
    }

    /**
     * Run the level until the player win, or until the player is out of lives.
     */
    public void run() {
        while (this.lives.getValue() > 0 && this.remainingBlocks.getValue() > 0) {
            this.oneTurn();
        }
    }

    /**
     * Run one turn of the level, until player win or dies.
     */
    public void oneTurn() {
        // set the balls and the paddle:
        List<Ball> l = this.createBallsOnTopOfPaddle();
        Paddle p = this.createPaddle();

        // Run the countdown:
        this.runner.run(new CountdownAnimation(2.0, 3, this.sprites, this.runner.getFps()));

        // Run the turn:
        this.runner.run(this);

        // If no balls remains, remove 1 life:
        if (this.remainingBalls.getValue() == 0) {
            this.lives.decrease(1);
        }

        // Remove remaining balls and paddle before moving to the next turn or level:
        for (Ball b : l) {
            b.removeFromGame(this);
        }
        this.remainingBalls.decrease(this.remainingBalls.getValue());
        p.removeFromGame(this);

        // If level is over - remove remaining blocks:
        if (this.remainingBlocks.getValue() <= 0) {
            for (Block b : this.level.blocks()) {
                b.removeFromGame(this);
            }
        }
    }

    /**
     * Create the borders of the game.
     * @param width The width of the GUI
     * @param height The height of the GUI
     */
    private void createBorders(int width, int height) {
        Block top = new Block(new Point(0, 0), width, 36);
        Block left = new Block(new Point(0, 30), 25, height);
        Block right = new Block(new Point(width - 25, 30), 25, height);
        top.setFill(new DrawColor(Color.gray));
        left.setFill(new DrawColor(Color.gray));
        right.setFill(new DrawColor(Color.gray));
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
    }

    /**
     * Add a collidable to the game environment.
     * @param c collidable
     */
    public void addToEnvirnoment(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add a sprite to the sprites collection.
     * @param s sprite.
     */
    public void addToSprites(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove a collidable from the game evironment.
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove a sprite from the sprites collection.
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface board, double dt) {
        // Draw all the sprites and notify them time has passed:
        this.sprites.drawOnAll(board);
        this.sprites.notifyAllTimePassed(dt);

        // If all blocks were destroyed, add 100 points.
        if (remainingBlocks.getValue() == 0) {
            this.score.increase(100);
        }

        // If p is pushed, run the pause screen:
        if (ks.isPressed("p")) {
            Animation ani = new PauseScreen(this.sprites);
            this.runner.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, ani));
            this.runner.run(new CountdownAnimation(1, 2, this.sprites, this.runner.getFps()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        // Will stop the runner in case there are less than 1 ball or 1 block.
        return !(this.remainingBlocks.getValue() > 0 && this.remainingBalls.getValue() > 0);
    }

    /**
     * Create the balls on top of the paddle, and return them in a list.
     * @return List<Ball>
     */
    public List<Ball> createBallsOnTopOfPaddle() {
        // Increase the remaining balls count:
        this.remainingBalls.increase(this.level.numberOfBalls());
        List<Ball> l = new java.util.ArrayList<>();
        // Create and initialize the balls:
        for (Velocity v : this.level.initialBallVelocities()) {
            Ball ball = new Ball((this.gui.getDrawSurface().getWidth() / 2),
                    this.gui.getDrawSurface().getHeight() - 34, 4, java.awt.Color.WHITE);
            ball.setVelocity(new Velocity(v.getDx(), v.getDy()));
            ball.setEnvironment(environment);
            ball.addToGame(this);
            l.add(ball);
        }
        // Return the balls in order to delete them when player finish the level.
        return l;
    }

    /**
     * Create the paddle. Return the paddle so it can be removed when player dies.
     * @return Paddle
     */
    public Paddle createPaddle() {
        // Create the paddle:
        Paddle p = new Paddle(new Point(this.gui.getDrawSurface().getWidth() / 2 - this.level.paddleWidth() / 2,
                this.gui.getDrawSurface().getHeight() - 30), this.level.paddleWidth(), this.ks);
        p.setBorders(24, this.gui.getDrawSurface().getWidth() - 24);
        p.setSpeed(this.level.paddleSpeed());
        p.addToGame(this);
        return p;
    }
}
