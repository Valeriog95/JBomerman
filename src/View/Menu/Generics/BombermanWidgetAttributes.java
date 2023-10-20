/* Projects : JBomberman
 * Created 30/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Generics;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Widget attribute interface
 */
public interface BombermanWidgetAttributes {

    /** Path of bomberman font */
    String FONT_PATH = "data/BombermanFont.ttf";
    /** color of bomberman font */
    Color TEXT_COLOR = new Color(189, 189, 0);
    /** default button font size */
    int BUTTON_FONT_SIZE = 32;
    /** default label font size */
    int LABEL_FONT_SIZE = 24;

    /**
     * Get bomberman font
     * @param size size of bomberman font
     * @return bomberman font type
     */
    static Font setBombermanFont(int size) {
        try {
            return (Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH)).deriveFont(Font.PLAIN, size));

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Useful method that return maximum size of the font for a widget of certain dimension passed as argumetn
     * @param dimension Dimension of the widget
     * @param text text to be written
     * @return max size of the font as an integer
     */
    static int getMaxFontSize(Dimension dimension, String text) {

        int fontSize = 2;

        // Create a dummy Label
        JLabel label = new JLabel();
        Font font = setBombermanFont(fontSize);

        if ( font == null ) return 0;

        label.setFont(font);
        label.setText(text);

        while (label.getPreferredSize().width < dimension.width && label.getPreferredSize().height < dimension.height) {
            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), fontSize++));
        }

        return fontSize - 2 ;

    }
}
