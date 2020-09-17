package gameobjects;

import java.io.IOException;
import java.util.List;

import animations.Animation;
import animations.GameLevel;
import animations.GameOverScreen;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import animations.WinningScreen;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
/**
 * GameFlow object that runs several levels.
 * @author Naveh Marchoom
 */
public class GameFlow {

    private GUI gui;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private HighScoresTable scores;

    private Counter lives = new Counter(7);
    private Counter score = new Counter(0);

    /**
     * Construct a new GameFlow object with given variables.
     * @param ar AnimationRunner
     * @param ks KeyboardSensor
     * @param hst HighScoreTable
     * @param g GUI
     */
    public GameFlow(GUI g, AnimationRunner ar, KeyboardSensor ks, HighScoresTable hst) {
        this.gui = g;
        this.ar = ar;
        this.ks = ks;
        this.scores = hst;
    }

    /**
     * Construct a new GameFlow object without giving variables.
     */
    public GameFlow() {
        this.gui = new GUI("Arcanoid", 800, 600);
        this.ar = new AnimationRunner(this.gui, 60);
        this.ks = this.gui.getKeyboardSensor();
    }

    /**
     * Run the levels in the levels list.
     * @param levels List<LevelInformation>
     */
    public void runLevels(List<LevelInformation> levels) {
        GameLevel game = new GameLevel(this.ar, this.gui, this.ks);
        DialogManager dm = this.gui.getDialogManager();
        Animation ani = new GameOverScreen(this.ks, this.score.getValue());
        this.score.decrease(this.score.getValue()); // Reset Score to 0.
        this.lives.increase(7 - this.lives.getValue()); // Reset Lives to 7.
        // Create the levels and run them:
        for (LevelInformation level : levels) {
            game.initialize(800, 600, level, this.score, this.lives);
            game.run();
            if (lives.getValue() == 0) {
                ani = new GameOverScreen(this.ks, this.score.getValue());
                break;
            }
        }
        if (lives.getValue() > 0) {
            ani = new WinningScreen(this.score.getValue());
        }
        this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, ani));
        // Saves the player's score:
        String name = dm.showQuestionDialog("Name", "What is your name?", "");
        this.scores.add(new ScoreInfo(name, this.score.getValue()));
        try {
            scores.save(new java.io.File("./score.ser"));
        } catch (IOException e) {
            System.out.println("Error saving the score file.");
            e.printStackTrace();
        }
        ani = new HighScoresAnimation(scores);
    }
}
