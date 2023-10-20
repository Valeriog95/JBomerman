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
 * Portal block
 */
public class Portal extends Block{
    private static List<ImageIcon> images;

    /**
     * Load Portal images
     */
    public static void setImages(){
        images = new ArrayList<>();
        try {
            Block.getFiles("Portal").forEach(
                    f -> images.add(Block.resize(new ImageIcon(f.getPath())))
            );
        } catch (Exception e){
            throw new ImageNotFoundException("Warning Portal.png image not found");
        }
    }

    /**
     * Constructor of class Portal
     */
    public Portal() {
        super.setIcon(images.get(Block.level-1));
    }

}
