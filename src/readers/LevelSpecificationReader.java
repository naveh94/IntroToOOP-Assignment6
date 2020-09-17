package readers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import blockcreator.BlocksFromSymbolsFactory;
import gameobjects.Block;
import gameobjects.Sprite;
import geometry.Velocity;
import levels.DynamicLevel;
import levels.LevelInformation;
import sprites.ImageSprite;
import sprites.RectangleSprite;

/**
 * LevelSpecificationReader object.
 * @author Naveh Marchoom
 */
public class LevelSpecificationReader {

    /**
     * Creates a new List<LevelInformation> out from a reader.
     * @param reader Reader
     * @return List<LevelInformation>
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levels = new ArrayList<>();
        LineNumberReader lineReader = new LineNumberReader(reader);
        String line = null;
        List<Block> blocks = null;
        boolean blockReader = false;
        DynamicLevel level = null;
        BlocksFromSymbolsFactory bfsf = null;
        int startX = 0, startY = 0, rowHeight = 0;
        try {
            while ((line = lineReader.readLine()) != null) {
                if (line.trim().equals("") || line.startsWith("#")) {
                    continue;
                }
                if (line.equals("START_LEVEL")) {
                    level = new DynamicLevel();
                    continue;
                }
                if (line.equals("END_LEVEL")) {
                    levels.add(level);
                    continue;
                }
                if (line.equals("START_BLOCKS")) {
                    blocks = new ArrayList<Block>();
                    blockReader = true;
                    continue;
                }
                if (line.equals("END_BLOCKS")) {
                    blockReader = false;
                    level.setBlocks(blocks);
                    continue;
                }
                String[] kv = line.split(":");
                String key = kv[0], value = null;
                if (kv.length > 1) {
                    value = kv[1];
                }
                if (key.equals("block_definitions")) {
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(value);
                    bfsf = BlocksDefinitionReader.fromReader(new InputStreamReader(is));
                    continue;
                }
                if (key.equals("level_name")) {
                    level.setLevelName(value);
                    continue;
                }
                if (key.equals("ball_velocities")) {
                    String[] values = value.split(" ");
                    List<Velocity> vels = new ArrayList<>();
                    for (int i = 0; i < values.length; i++) {
                        String[] ans = values[i].split(",");
                        double angle = Double.parseDouble(ans[0]), speed = Double.parseDouble(ans[1]);
                        vels.add(Velocity.fromAngleAndSpeed(Velocity.straightUpAngle() + angle, speed));
                    }
                    level.setInitialBallVelocities(vels);
                    level.setNumberOfBalls(vels.size());
                    continue;
                }
                if (key.equals("background")) {
                    if (value.startsWith("image")) {
                        Sprite bg = ImageSprite.fromString(value.substring(6).replace(")", ""));
                        level.setBackground(bg);
                        continue;
                    } else if (value.startsWith("color")) {
                        Sprite bg = RectangleSprite.fromString(value.substring(6).replace(")", ""));
                        level.setBackground(bg);
                        continue;
                    }
                }
                if (key.equals("paddle_speed")) {
                    level.setPaddleSpeed(Integer.parseInt(value));
                    continue;
                }
                if (key.equals("paddle_width")) {
                    level.setPaddleWidth(Integer.parseInt(value));
                    continue;
                }
                if (key.equals("num_blocks")) {
                    level.setNumberOfBlocksToRemove(Integer.parseInt(value));
                    continue;
                }
                if (key.equals("blocks_start_x")) {
                    startX = Integer.parseInt(value);
                    continue;
                }
                if (key.equals("blocks_start_y")) {
                    startY = Integer.parseInt(value);
                    continue;
                }
                if (key.equals("row_height")) {
                    rowHeight = Integer.parseInt(value);
                    continue;
                }
                if (blockReader) {
                    blocks.addAll(bfsf.readLine(line, startX, startY));
                    startY += rowHeight;
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed reading file.");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Failed closing the reader.");
                e.printStackTrace();
            }
        }
        return levels;
    }
}
