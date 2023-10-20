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
 * Monster block
 */
public class Monster extends AnimatedBlock{

    private static List<ImageIcon> images;

    /**
     * Load Monster images
     */
    public static void setImages(){
        images = new ArrayList<>();
        try {
            Block.getFiles("Monster").forEach(
                    f -> images.add(Block.resize(new ImageIcon(f.getPath())))
            );
        } catch (Exception e){
            throw new ImageNotFoundException("Warning Monster.png image not found");
        }
    }

    /**
     * Constructor of class Monster
     */
    public Monster() {
        super.setIcon(images.get(Block.level-1));
//        imgSwitcher = new Thread(this::switchImage);
//        imgSwitcher.start();
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
