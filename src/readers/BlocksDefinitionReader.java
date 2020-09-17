package readers;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import animations.Effects;
import blockcreator.BlocksFromSymbolsFactory;
import blockcreator.CreateBlock;
import blockcreator.DrawColor;
import blockcreator.DrawImage;
import blockcreator.DrawStroke;
import blockcreator.Drawer;

/**
 * Block Definition Reader.
 * Doesn't have a builder.
 * @author Naveh Marchoom
 */
public class BlocksDefinitionReader {

    /**
     * Creates a new Block factory getting the parameters from a reader.
     * @param reader Reader
     * @return BlocksFromSymbolFactory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory bfsf = new BlocksFromSymbolsFactory();
        LineNumberReader lineReader = new LineNumberReader(reader);
        String line = null;
        int defHeight = 0, defWidth = 0, defHP = 0;
        Drawer defFill = null, defStroke = null;
        Map<Integer, Drawer> defFillK = new HashMap<>();
        try {
            while ((line = lineReader.readLine()) != null) {
                if (line.trim().equals("") || line.startsWith("#")) {
                    continue;
                }
                String[] values = line.split(" ");
                if (values[0].equals("default")) { // Setting Default values:
                    for (int i = 1; i < values.length; i++) {
                        String[] knv = values[i].split(":");
                        String key = knv[0], value = knv[1];
                        if (key.equals("height")) {
                            defHeight = Integer.parseInt(value);
                            continue;
                        }
                        if (key.equals("width")) {
                            defWidth = Integer.parseInt(value);
                            continue;
                        }
                        if (key.equals("hit_points")) {
                            defHP = Integer.parseInt(value);
                            continue;
                        }
                        if (key.equals("fill")) {
                            defFill = getDrawer(value);
                            continue;
                        }
                        if (key.startsWith("fill-")) {
                            String[] keyNum = key.split("-");
                            defFillK.put(Integer.parseInt(keyNum[1]), getDrawer(value));
                            continue;
                        }
                        if (key.equals("stroke")) {
                            defStroke = new DrawStroke(getColor(value.substring(6).replace(")", "")));
                            continue;
                        }
                     }
                } else if (values[0].equals("bdef")) { // Adding a block definition.
                    int width = defWidth, height = defHeight, hp = defHP;
                    Drawer fill = defFill, stroke = defStroke;
                    Map<Integer, Drawer> fillK = new HashMap<Integer, Drawer>();
                    fillK.putAll(defFillK);
                    String symbol = null;
                    for (int i = 1; i < values.length; i++) {
                        String[] knv = values[i].split(":");
                        String key = knv[0], value = knv[1];
                        if (key.equals("height")) {
                            height = Integer.parseInt(value);
                            continue;
                        }
                        if (key.equals("width")) {
                            width = Integer.parseInt(value);
                            continue;
                        }
                        if (key.equals("hit_points")) {
                            hp = Integer.parseInt(value);
                            continue;
                        }
                        if (key.equals("fill")) {
                            fill = getDrawer(value);
                            continue;
                        }
                        if (key.startsWith("fill-")) {
                            String[] keyNum = key.split("-");
                            fillK.put(Integer.parseInt(keyNum[1]), getDrawer(value));
                            continue;
                        }
                        if (key.equals("stroke")) {
                            stroke = new DrawStroke(getColor(value.substring(6).replace(")", "")));
                            continue;
                        }
                        if (key.equals("symbol")) {
                            symbol = value;
                        }
                    }
                    CreateBlock bc = new CreateBlock(width, height, hp, fill, stroke, fillK);
                    bfsf.addBlockCreator(symbol, bc);
                } else if (values[0].equals("sdef")) { // Adding a spacer definition.
                    String symbol = null;
                    int width = 0;
                    for (int i = 1; i < values.length; i++) {
                        String[] knv = values[i].split(":");
                        String key = knv[0], value = knv[1];
                        if (key.equals("symbol")) {
                            symbol = value;
                            continue;
                        }
                        if (key.equals("width")) {
                            width = Integer.parseInt(value);
                            continue;
                        }
                    }
                    bfsf.addSpacer(symbol, width);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed reading file.");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Failed closing the reader.");
                e.printStackTrace();
            }
        }
        return bfsf;
    }

    /**
     * Return a color based on the string given.
     * If String is in 'RBG(r,b,g' format, returns a new Color(r,b,g).
     * If string is in name format, will use Effects.colorFromString to get the right color.
     * @param s String
     * @return new Color.
     */
    public static Color getColor(String s) {
        if (s.startsWith("RGB(")) {
            String[] rgb = s.substring(4).split(",");
            return new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
        } else {
            return Effects.colorFromString(s);
        }
    }

    /**
     * Creates a Drawer based on given String.
     * If starts with "image(" will make an image Drawer.
     * If starts with "color(" will make a color Drawer.
     * @param s String
     * @return new Drawer.
     */
    public static Drawer getDrawer(String s) {
        if (s.startsWith("image")) {
            InputStream is = null;
            Image img = null;
            try {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(s.substring(6).replace(")", ""));
                img = ImageIO.read(is);
                return new DrawImage(img);
            } catch (IOException e) {
                System.out.println("Error reading image.");
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        System.out.println("Failed closing file '"
                                + s.substring(6).replace(")", "") + "'.");
                        e.printStackTrace();
                    }
                }
            }
        } else if (s.startsWith("color")) {
            return new DrawColor(getColor(s.substring(6).replace(")", "")));
        }
        return null;
    }
}
