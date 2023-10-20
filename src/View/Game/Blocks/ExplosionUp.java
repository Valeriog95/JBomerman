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
 * Explosion up block
 */
public class ExplosionUp extends Explosion{
    private static List<ImageIcon> images;

    /**
     * Load Explosion Up images
     */
    public static void setImages() throws ImageNotFoundException {

        images = Explosion.setFinalImages("Up");

    }

    /**
     * Constructor of class ExplosionUp
     */
    public ExplosionUp() {
        super.setIcon(images.get(imgIndex));
        imgSwitcher = new Thread(this::switchImage);
        imgSwitcher.start();
    }

    @Override
    protected void switchImage() {
        while(true){
            if(++imgIndex == images.size()) imgIndex = 0;
            try {
                Thread.sleep(SWITCHING_INTERVAL);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            super.setIcon(images.get(imgIndex));
        }
    }
}
