package blockcreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameobjects.Block;
/**
 * An object for creating blocks from symbols.
 * @author Naveh Marchoom
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacersWidth;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Creates a new BlockFromSymbolsFactory.
     */
    public BlocksFromSymbolsFactory() {
        spacersWidth = new HashMap<String, Integer>();
        blockCreators = new HashMap<String, BlockCreator>();
    }

    /**
     * Add a spacer to the spacers map.
     * @param symbol String
     * @param width int
     */
    public void addSpacer(String symbol, int width) {
        this.spacersWidth.put(symbol, width);
    }

    /**
     * Add a block creator to the block creators map.
     * @param symbol String
     * @param bc BlockCreator
     */
    public void addBlockCreator(String symbol, BlockCreator bc) {
        this.blockCreators.put(symbol, bc);
    }

    /**
     * Check if String s is a symbol for Spacer.
     * @param s String
     * @return true if s exist in Spacers map, else returns false.
     */
    public boolean isSpaceSymbol(String s) {
        return spacersWidth.containsKey(s);
    }

    /**
     * Check if String s is a symbol for block.
     * @param s String
     * @return true if s exist in BlockCreators map. Else will return false.
     */
    public boolean isBlockSybmol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * Create a new block using the symbol, and the parameters given.
     * @param s String
     * @param xPos int
     * @param yPos int
     * @return Block
     */
    public Block getBlock(String s, int xPos, int yPos) {
        if (this.isBlockSybmol(s)) {
            BlockCreator bc = this.blockCreators.get(s);
            return bc.create(xPos, yPos);
        }
        return null;
    }

    /**
     * Return the space width is a symbol for.
     * @param s String
     * @return int
     */
    public int getSpaceWidth(String s) {
        return this.spacersWidth.get(s);
    }

    /**
     * Read a string line and return a line of blocks.
     * Starts at the c
     * @param s String
     * @param xStart int
     * @param yStart int
     * @return List<Block>
     */
    public List<Block> readLine(String s, int xStart, int yStart) {
        int xPos = xStart, yPos = yStart;
        List<Block> blocks = new ArrayList<>();
        int width = 0;
        for (int i = 0; i < s.length(); i++) {
            String symbol = "" + s.charAt(i);
            if (this.isBlockSybmol(symbol)) {
                Block b = this.getBlock(symbol, xPos, yPos);
                width = (int) b.getCollisionRectangle().getWidth();
                xPos += width;
                blocks.add(b);
                continue;
            }
            if (this.isSpaceSymbol(symbol)) {
                xPos += this.getSpaceWidth(symbol);
            }
        }
        return blocks;
    }
}
