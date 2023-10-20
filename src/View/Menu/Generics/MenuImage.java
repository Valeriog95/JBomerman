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
 * Menu image label
 */
public class MenuImage extends JLabel {

    private static final String path = "data/images/menu/MenuImage.png";

    /**
     * Constructor of menu image
     * @param dimension dimension of the widget
     */
    public MenuImage(Dimension dimension) {
        super(new ImageIcon(
            Toolkit.getDefaultToolkit()
                    .getImage(path)
                    .getScaledInstance(
                        (int) dimension.getWidth(),
                        (int) dimension.getHeight(),
                        Image.SCALE_SMOOTH)
        ));
        setBackground(Color.BLACK);

    }
}
