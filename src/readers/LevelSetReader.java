package readers;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import gameobjects.GameFlow;
import gameobjects.Task;
import pregame.Menu;
import pregame.RunLevelSet;

/**
 * LevelSetReader object for reading LevelSets files.
 * @author Naveh Marchoom
 *
 */
public class LevelSetReader {

    /**
     * Updates given menu with given levelsets file.
     * @param reader Reader
     * @param menu Menu<Task<Void>>
     * @param highScore Task<Void>
     * @param game gameFlow
     */
    public void fromReader(Reader reader, Menu<Task<Void>> menu, Task<Void> highScore, GameFlow game) {
        LineNumberReader lineReader = new LineNumberReader(reader);
        String line = null;
        int lineNum = 1;
        String symbol = null, message = null;
        try {
            while ((line = lineReader.readLine()) != null) {
                if (lineNum % 2 == 1) {
                    String[] knv = line.split(":");
                    if (knv.length < 1) {
                        System.out.println("level_set file doesn't follow the level_set regulations.");
                    }
                    symbol = knv[0];
                    message = knv[1];
                } else {
                    menu.addSelection(symbol, message, new RunLevelSet(line, game, highScore));
                }
                lineNum++;
            }
        } catch (IOException e) {
            System.out.println("Failed reading file.");
            e.printStackTrace();
        }
    }
}
