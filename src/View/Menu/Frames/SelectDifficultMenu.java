/* Projects : JBomberman
 * Created 24/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Frames;

import Controller.BombermanController;
import Controller.Menu.StartGameController;
import View.BombermanView;
import View.Menu.Generics.BombermanButton;
import View.Menu.Generics.BombermanMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Select difficult window
 */
public class SelectDifficultMenu extends BombermanMenu implements BombermanView<StartGameController> {

    /* Windows widgets */
    private final BombermanButton easy;
    private final BombermanButton medium;
    private final BombermanButton hard;

    /**
     * Constructor of select difficult menu window
     */
    public SelectDifficultMenu(){

        /* Preliminary Operations */
        super();
        Dimension button_dimension = getStandardButtonDimension();

        // 1. Create and place widgets
        easy = new BombermanButton("EASY", button_dimension);
        medium = new BombermanButton("MEDIUM", button_dimension);
        hard = new BombermanButton("HARD", button_dimension);

        // 2. Add components
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(easy);
        buttonPanel.add(medium);
        buttonPanel.add(hard);

        // 3. Place component and create window
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        add(backgroundPanel);
        setSize(screenSize.width, screenSize.height);
        setVisible(true);

    }

    private void setEasyActionPerformed(ActionListener listener){ easy.addActionListener(listener); }
    private void setMediumActionPerformed(ActionListener listener){ medium.addActionListener(listener); }
    private void setHardActionPerformed(ActionListener listener){ hard.addActionListener(listener); }

    @Override
    public void setActionsPerformed(StartGameController controller) {
        setEasyActionPerformed(controller.getStartEasyGameAction());
        setMediumActionPerformed(controller.getStartMediumGameAction());
        setHardActionPerformed(controller.getStartHardGameAction());
    }
}
