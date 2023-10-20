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
 * Block that continue right the explosion
 */
public class ExplosionContinueRight extends Explosion{
    private static List<ImageIcon> images;

    /**
     * Load Explosion Continue right images
     */
    public static void setImages() throws ImageNotFoundException {

        images = Explosion.setContinueImages("Right");
    }

    /**
     * Constructor of class ExplosionContinueRight
     */
    public ExplosionContinueRight() {
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
