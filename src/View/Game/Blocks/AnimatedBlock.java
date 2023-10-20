/* Projects : JBomberman
 * Created 22/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Game.Blocks;

/**
 * Class animated block, used for block that change state
 */
public abstract class AnimatedBlock extends Block{
    /** switching time in millisecond */
    protected static final int SWITCHING_INTERVAL = 500;
    /** index of current image showed */
    protected int imgIndex;
    /** Process that switch image */
    protected Thread imgSwitcher;

    /**
     * Switch between different images in order to make it animated
     */
    protected abstract void switchImage();
}
