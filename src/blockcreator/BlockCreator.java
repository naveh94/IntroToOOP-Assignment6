package blockcreator;

import gameobjects.Block;
/**
 * Block Creator interface.
 * @author Naveh Marchoom
 *
 */
public interface BlockCreator {

    /**
     * Creates a new block in given position.
     * @param xPos int
     * @param yPos int
     * @return Block
     */
    Block create(int xPos, int yPos);

}
