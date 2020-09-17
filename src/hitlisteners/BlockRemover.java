package hitlisteners;

import animations.GameLevel;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Counter;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author Naveh Marchoom
 *
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Create a new BlockRemover object using given parameters.
     * @param game GameLevel
     * @param removedBlocks Counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitpoints() == 0) {
            beingHit.removeFromGame(game);
            this.remainingBlocks.decrease(1);
        }
    }

}
