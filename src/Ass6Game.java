import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameobjects.AnimationRunner;
import gameobjects.GameFlow;
import gameobjects.HighScoresTable;
import gameobjects.Task;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;
import pregame.MenuAnimation;
import readers.LevelSetReader;

/**
 * The main class running the game.
 * @author Naveh Marchoom
 *
 */
public class Ass6Game {

    /**
     * Run the game.
     * If args empty, run levels 1-4. Else, will run levels in argument.
     * @param args levelSet arguments.
     */
    public static void main(String[] args) {
        String levelSetPath = "level_sets.txt";
        if (args.length > 0) {
            levelSetPath = args[0];
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        KeyboardSensor ks = gui.getKeyboardSensor();
        HighScoresTable hst = initializeSaveFile();
        GameFlow game = new GameFlow(gui, runner, ks, hst);
        runMenu(ks, runner, game, hst, levelSetPath);
    }

    /**
     * Initialize the save file, if exist load it up, else will create a new one.
     * @return HighScoresTable
     */
    private static HighScoresTable initializeSaveFile() {
        HighScoresTable hst = new HighScoresTable(14);
        File saveFile = new File("./score.ser");
        if (saveFile.exists()) {
            try {
                hst.load(saveFile);
            } catch (IOException e) {
                System.out.println("Error loading the save file.");
                e.printStackTrace();
            }
        } else {
            try {
                hst.save(saveFile);
            } catch (IOException e) {
                System.out.println("Error creating a new save file.");
                e.printStackTrace();
            }
        }
        return hst;
    }

    /**
     * Run the main menu for the game.
     * @param ks KeyboardSensor
     * @param runner AnimationRunner
     * @param game GameFlow
     * @param scores HighScoresTable
     * @param levelSetPath String
     */
    private static void runMenu(KeyboardSensor ks, AnimationRunner runner, GameFlow game,
            HighScoresTable scores, String levelSetPath) {
        Task<Void> quit = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        };

        Task<Void> highScores = new Task<Void>() {
            @Override
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(scores)));
                return null;
            }
        };

        Task<Void> classicLevels = new Task<Void>() {
            @Override
            public Void run() {
                List<LevelInformation> l = new ArrayList<>();
                l.add(new DirectHit());
                l.add(new WideEasy());
                l.add(new Green3());
                l.add(new FinalFour());
                game.runLevels(l);
                highScores.run();
                return null;
            }
        };

        MenuAnimation<Task<Void>> levelSetsMenu =
                new MenuAnimation<Task<Void>>("Choose level set:", ks, runner);
        LevelSetReader lsr = new LevelSetReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetPath);
        if (is == null) {
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't find file '" + levelSetPath + "'.");
                e.printStackTrace();
            }
        }
        lsr.fromReader(new InputStreamReader(is), levelSetsMenu, highScores, game);
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            System.out.println("Failed closing file.");
            e.printStackTrace();
        }
        MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>("Menu", ks, runner);
        menu.addSubMenu("s", "tart Game", levelSetsMenu);
        menu.addSelection("a", "ssingment 5 Levels", classicLevels);
        menu.addSelection("h", "igh Score", highScores);
        menu.addSelection("q", "uit Game", quit);

        while (true) {
            runner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu.resetStop();
        }
    }
}
