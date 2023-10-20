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
 * ImprovePower power up block
 */
public class PowerUpImprovePower extends PowerUp{
    private static List<ImageIcon> images;

    /**
     * Load PowerUp images
     */
    public static void setImages() throws ImageNotFoundException {
        images = PowerUp.setPowerUpImages("ImprovePower");
    }

    /**
     * Constructor of class PowerUp
     */
    public PowerUpImprovePower() {
        super.setIcon(images.get(0));
    }
}
