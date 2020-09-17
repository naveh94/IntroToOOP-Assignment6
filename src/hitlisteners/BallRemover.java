package hitlisteners;

import animations.GameLevel;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Counter;

/**
 * Remove Balls when reach the death zone.
 * @author Naveh Marchoom
 *
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Create a BallRemover object using given parameters.
     * @param game GameLevel
     * @param removedBalls Counter
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}
