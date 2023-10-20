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
 * Generic power up block
 */
public abstract class PowerUp extends Block{

    /**
     * Useful mehod that find image of specialized power up
     * @param type power up type as a string
     * @return list of image icons
     * @throws ImageNotFoundException exception raises when images are damaged or not found
     */
    protected static List<ImageIcon> setPowerUpImages(String type) throws ImageNotFoundException {
        List<ImageIcon> images = new ArrayList<>();
        try {
            Block.getFiles("PowerUp" + type).forEach(
                    f -> images.add(Block.resize(new ImageIcon(f.getPath())))
            );
        } catch (Exception e){
            throw new ImageNotFoundException("Warning PowerUp" + type + ".png image not found");
        }
        return images;
    }

    /**
     * Generic power up constructor
     */
    public PowerUp() {
    }
}
