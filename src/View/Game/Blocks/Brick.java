/* Projects : JBomberman
 * Created 22/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Game.Blocks;

import View.ImageNotFoundException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Brick block
 */
public class Brick extends Block{
    private static List<ImageIcon> images;

    /**
     * Load Brick images
     */
    public static void setImages(){

        images = new ArrayList<>();
        try {
            Block.getFiles("Brick").forEach(
                    f -> images.add(Block.resize(new ImageIcon(f.getPath())))
            );
        } catch (Exception e){
            throw new ImageNotFoundException("Warning Brick.png image not found");
        }
    }

    /**
     * Constructor of class Brick
     */
    public Brick() {
        super.setIcon(images.get(Block.level-1));
    }
}
