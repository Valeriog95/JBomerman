/* Projects : JBomberman
 * Created 30/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Generics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Bomberman entry widget
 */
public class BombermanTextField extends JTextField implements BombermanWidgetAttributes{

    private static final List<String> NICKNAMES = new ArrayList<>(
            List.of(
                    "BlazeMaster",
                    "BombasticBoom",
                    "ExplosiveChamp",
                    "Mr. TNT",
                    "BlastMaster5000",
                    "PyroWarrior",
                    "The Bomber King",
                    "DynamiteDynamo",
                    "InfernoBlast",
                    "DemolitionDevil"
            )
    );

    /**
     * Constructor of bomberman entry widget (used only in creater player window)
     * @param dimension dimension of that widget
     */
    public BombermanTextField(Dimension dimension) {
        super(30);
        setFont(BombermanWidgetAttributes.setBombermanFont(LABEL_FONT_SIZE));
        setText(NICKNAMES.get(new Random().nextInt(NICKNAMES.size())));
        setBackground(Color.BLACK);
        setForeground(TEXT_COLOR);
        setSize(dimension);
    }
}
