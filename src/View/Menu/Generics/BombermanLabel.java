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
 * Generic Bomberman Label
 */
public class BombermanLabel extends JLabel implements BombermanWidgetAttributes{

    /**
     * Constructor of bomberman label
     * @param dimension dimension of the label
     */
    public BombermanLabel(Dimension dimension) {
        super();
        setBackground(Color.BLACK);
        setForeground(TEXT_COLOR);
        setSize(dimension);
    }

    @Override
    public void setText(String text) {
        setFont(
                BombermanWidgetAttributes.setBombermanFont(
                        BombermanWidgetAttributes.LABEL_FONT_SIZE
                ));
        super.setText(text);
        setVerticalTextPosition(SwingConstants.CENTER);
    }

}
