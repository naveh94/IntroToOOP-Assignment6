package pregame;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import gameobjects.GameFlow;
import gameobjects.Task;
import levels.LevelInformation;
import readers.LevelSpecificationReader;

/**
 * A Task<Void> object for running levels out of a filePath.
 * @author Naveh Marchoom
 */
public class RunLevelSet implements Task<Void> {

    private String filename = null;
    private GameFlow game;
    private Task<Void> highscores;

    /**
     * Creates a new RunLevelSet task.
     * @param filePath String
     * @param flow GameFlow
     * @param highScoreTask Task<Void>
     */
    public RunLevelSet(String filePath, GameFlow flow, Task<Void> highScoreTask) {
        this.filename = filePath;
        this.game = flow;
        this.highscores = highScoreTask;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void run() {
        List<LevelInformation> l = initializeLevels(this.filename);
        this.game.runLevels(l);
        this.highscores.run();
        return null;
    }

    /**
     * Initialize the levels played using filePath, and put them in List<LevelInformation>.
     * @param filePath String
     * @return List<LevelInformation>
     */
    private static List<LevelInformation> initializeLevels(String filePath) {
        LevelSpecificationReader lsr = new LevelSpecificationReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
        return lsr.fromReader(new InputStreamReader(is));
    }

}
