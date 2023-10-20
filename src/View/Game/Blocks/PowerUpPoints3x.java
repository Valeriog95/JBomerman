/* Projects : JBomberman
 * Created 23/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Game.Blocks;

import View.ImageNotFoundException;

import javax.swing.*;
import java.util.List;

/**
 * 3x multiplier power up block
 */
public class PowerUpPoints3x extends PowerUp{
    private static List<ImageIcon> images;

    /**
     * Load PowerUp images
     */
    public static void setImages() throws ImageNotFoundException {
        images = PowerUp.setPowerUpImages("Points3x");
    }

    /**
     * Constructor of class PowerUp
     */
    public PowerUpPoints3x() {
        super.setIcon(images.get(0));
    }
}
