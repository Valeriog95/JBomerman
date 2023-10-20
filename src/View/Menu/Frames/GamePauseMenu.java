/* Projects : JBomberman
 * Created 02/10/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Frames;

import Controller.Game.GamePauseController;
import View.BombermanView;
import View.Menu.Generics.BombermanButton;
import View.Menu.Generics.BombermanMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Game pause window
 */
public class GamePauseMenu extends BombermanMenu implements BombermanView<GamePauseController> {

    /* Windows widgets */
    private final BombermanButton resume;
    private final BombermanButton exit;

    /**
     * Constructor of game pause window
     */
    public GamePauseMenu(){

        /* Preliminary Operations */
        super();
        Dimension button_dimension = getStandardButtonDimension();

        // 1. Create and place widgets
        resume = new BombermanButton("RESUME", button_dimension);
        exit = new BombermanButton("EXIT", button_dimension);

        // 2. Add components
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(resume);
        buttonPanel.add(exit);

        // 3. Place component and create window
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        add(backgroundPanel);
        setSize(screenSize.width, screenSize.height);
        setVisible(true);

    }

    private void setResumeActionPerformed(ActionListener listener){ resume.addActionListener(listener); }
    private void setExitActionPerformed(ActionListener listener){ exit.addActionListener(listener); }

    @Override
    public void setActionsPerformed(GamePauseController controller) {
        setResumeActionPerformed(controller.getResumeAction());
        setExitActionPerformed(controller.getExitAction());

    }
}
