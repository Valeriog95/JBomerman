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
 * Explosion block
 */
public abstract class Explosion extends AnimatedBlock{

    private static List<ImageIcon> findImages(String contains,boolean isFinal) throws ImageNotFoundException{
        List<ImageIcon> images = new ArrayList<>();
        try {
            Block.getFiles("Explosion")
                    .stream()
                    .filter(f -> f.toString().contains(contains) && (isFinal != f.toString().contains( "Continue" )))
                    .forEach(f -> images.add(Block.resize(new ImageIcon(f.getPath()))));
        } catch (Exception e){
            throw new ImageNotFoundException("Warning Explosion" + contains + ".png image not found");
        }
        return images;
    }

    /**
     * Set primary explosion images
     * @param contains string to be contained in file searched
     * @return list of image icon
     * @throws ImageNotFoundException exception raised if file are damaged or not found
     */
    protected static List<ImageIcon> setFinalImages(String contains) throws ImageNotFoundException {
        return findImages( contains, true );
    }

    /**
     * Set last explosion images
     * @param contains string to be contained in file searched
     * @return list of image icon
     * @throws ImageNotFoundException exception raised if file are damaged or not found
     */
    protected static List<ImageIcon> setContinueImages(String contains) throws ImageNotFoundException{
        return findImages( contains, false);
    }

    /**
     * Default explosion constructor
     */
    public Explosion() {}
}
