/* Projects : JBomberman
 * Created 22/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Game.Blocks;

import Model.Game.Coordinate;
import View.Game.Avatar;
import View.ImageNotFoundException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Player block
 */
public class Player extends OrientedBlock{
    private static HashMap<Avatar,HashMap<String, List<ImageIcon>>> images;
    private static Avatar avatar = Avatar.Red;
    private static Coordinate position;
    private static String direction = "center";

    /**
     * Set image of current class
     */
    public static void setImages(){

        /* Create Blank variables and relative keys*/
        images = new HashMap<>(Map.of(
                Avatar.Red, new HashMap<>(),
                Avatar.Blue, new HashMap<>(),
                Avatar.Black, new HashMap<>(),
                Avatar.White, new HashMap<>()
        ));

        images.keySet().forEach(
                k -> images.get(k).putAll(
                        Map.of(
                                "up",new ArrayList<>(),
                                "down",new ArrayList<>(),
                                "left",new ArrayList<>(),
                                "right",new ArrayList<>(),
                                "center",new ArrayList<>(),
                                "dead",new ArrayList<>()
                        )
                )
        );

        /* Populate them */
        try {
            Block.getFiles("Player").forEach(
                    f -> {
                        String name = f.toString();
                        String orientation = name.split("_")[1].replace(".png","");
                        if(name.contains("Red")){
                            images.get(Avatar.Red).get(orientation).add(Block.resize(new ImageIcon(f.getPath())));
                        } else if (name.contains("Black")) {
                            images.get(Avatar.Black).get(orientation).add(Block.resize(new ImageIcon(f.getPath())));
                        }else if (name.contains("White")) {
                            images.get(Avatar.White).get(orientation).add(Block.resize(new ImageIcon(f.getPath())));
                        }else if (name.contains("Blue")){
                            images.get(Avatar.Blue).get(orientation).add(Block.resize(new ImageIcon(f.getPath())));
                        }
                    }
            );
        } catch (Exception e){
            /* Attention! It would be better to handle different exceptions specifically rather than a general one */
            throw new ImageNotFoundException("Warning Player.png image not found");
        }
    }

    /**
     * Set avatar colour
     * @param avatar avatar to be set
     */
    public static void setAvatar(String avatar) {
        Player.avatar = Avatar.valueOf(avatar);
    }

    /**
     * Constructor of Player
     */
    public Player() {
        super.setIcon(images.get(avatar).get(direction).get(imgIndex));
        imgSwitcher = new Thread(this::switchImage);
        imgSwitcher.start();
    }

    /**
     * Set player as dead (runs different images)
     */
    public static void setDead() {
        direction = "dead";
    }

    /**
     * Set player as alive
     */
    public static void setAlive() {
        direction = "center";
    }

    @Override
    public void setCurrentPosition(Coordinate currentPosition) {
        if (position == null){
            position = currentPosition;
            direction = "center";
        }
        else{
            direction = Coordinate.getDirection(position,currentPosition).toLowerCase();
            position = currentPosition;
        }
    }

    @Override
    protected void switchImage() {
        while(true){
            if(++imgIndex == images.get(avatar).get(direction).size()) imgIndex = 0;
            try { Thread.sleep(SWITCHING_INTERVAL); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
            try {super.setIcon( images.get( avatar ).get( direction ).get( imgIndex ) );}
            catch (Exception e ) { continue; }
        }
    }
}
