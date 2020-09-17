package hitlisteners;

import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Counter;

/**
 * Score Tracker.
 * @author Naveh Marchoom
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Create a new Score Tracking Listener.
     * @param scoreCounter Counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitpoints() > 0) {
            currentScore.increase(5);
        }
        if (beingHit.getHitpoints() == 0) {
            currentScore.increase(10);
        }
    }
}
