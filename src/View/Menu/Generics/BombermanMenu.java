/* Projects : JBomberman
 * Created 24/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Generics;

import javax.swing.*;
import java.awt.*;

/**
 * Generic bomberman menu, a Jframe that contains only menu image in the upper side of the window and a background panel
 * that will contain bomberman button, labels etc.
 */
public abstract class BombermanMenu extends JFrame {

    private static final float MENU_WIDTH = 0.95F;
    private static final float MENU_HEIGHT = 0.50F;

    private static final float BUTTON_WIDTH = 0.30F;
    private static final float BUTTON_HEIGHT = 0.10F;

    /** Current dimension of the screen */
    protected final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /** Menu image label */
    protected final MenuImage menuImage;
    /** Background panel of menu window*/
    protected final JPanel backgroundPanel;

    /**
     * Return dimension of standard menu button
     * @return button dimension
     */
    protected Dimension getStandardButtonDimension(){
        return new Dimension(
                (int) (screenSize.getWidth() * BUTTON_WIDTH),
                (int) (screenSize.getHeight()* BUTTON_HEIGHT)
        );
    }

    /**
     * Return dimension of standard menu image
     * @return image dimension
     */
    protected Dimension menuImageDimension(){
        return new Dimension(
                (int) (screenSize.getWidth() * MENU_WIDTH),
                (int) (screenSize.getHeight()* MENU_HEIGHT)
        );
    }

    /**
     * Constructor of bomberman Menu
     */
    public BombermanMenu(){

        // Default settings
        setTitle("JBomberman Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create background Panel
        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.BLACK);
        backgroundPanel.setLayout(new BorderLayout());
        menuImage = new MenuImage(menuImageDimension());

        // Insert image on the upper side of the menu window
        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(Color.BLACK);
        upperPanel.add(menuImage);
        backgroundPanel.add(upperPanel, BorderLayout.NORTH);

    }

}
