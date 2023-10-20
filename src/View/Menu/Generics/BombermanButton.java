/* Projects : JBomberman
 * Created 30/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Generics;

import javax.swing.*;
import java.awt.*;

/**
 * Generic Bomberman button
 */
public class BombermanButton extends JButton implements BombermanWidgetAttributes {

    /**
     * Constructor of generic bomberman button
     * @param text text showed in the button
     * @param dimension dimension of the button
     */
    public BombermanButton(String text, Dimension dimension) {
        super();
        setFont(
                BombermanWidgetAttributes.setBombermanFont(
                        BombermanWidgetAttributes.BUTTON_FONT_SIZE
                ));
        setBackground(Color.BLACK);
        setForeground(TEXT_COLOR);
        setText(text);
        setSize(dimension);
    }
}
