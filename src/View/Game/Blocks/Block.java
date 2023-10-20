/* Projects : JBomberman
 * Created 21/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Game.Blocks;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Base class Block
 */
public abstract class Block extends JLabel {

    private static final String PATH = "data/images/game/Blocks";
    /** Dimension of generic Block */
    protected static Dimension dimension;
    /** Level of generic Block, every level has a different image for certain blocks */
    protected static int level = 1;

    /**
     * Search png files inside the PATH
     * @param searched Name of files searched
     * @return list of file founded
     */
    protected static List<File> getFiles(String searched){
        return Stream.of(Objects.requireNonNull(new File(Block.PATH).listFiles()))
                .filter(f->f.toString().contains(searched))
                .toList();
    }

    /**
     * Resize image passed as argument according to Block. Dimension value
     * @param i image to be resized
     * @return image resized
     */
    public static ImageIcon resize(ImageIcon i){
        return new ImageIcon(
                i.getImage().getScaledInstance(dimension.width,dimension.height,Image.SCALE_SMOOTH)
        );
    }

    /**
     * Set Dimensione of Whole Blocks
     * @param dimension dimension of Block
     */
    public static void setDimension(Dimension dimension) {
        Block.dimension = dimension;
    }

    /**
     * Set level
     *
     * @param level level setted
     */
    public static void setLevel(int level) {
        Block.level = level;
    }

    /**
     * Generic block constructor
     */
    public Block() {super();}
}
