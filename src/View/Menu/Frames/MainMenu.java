/* Projects : JBomberman
 * Created 24/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Frames;

import Controller.Menu.MainMenuController;
import View.BombermanView;
import View.Menu.Generics.BombermanButton;
import View.Menu.Generics.BombermanMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Main menu window
 */
public class MainMenu extends BombermanMenu implements BombermanView <MainMenuController>{

    /* buttons */
    private final BombermanButton startGame;
    private final BombermanButton selectPlayer;
    private final BombermanButton createPlayer;
    private final BombermanButton exit;

    /**
     * Constructor of main menu window
     */
    public MainMenu(){

        /* Preliminary Operations */
        super();
        Dimension button_dimension = getStandardButtonDimension();

        // 1. Create and place widgets
        startGame = new BombermanButton("START GAME", button_dimension);
        selectPlayer = new BombermanButton("SELECT PLAYER", button_dimension);
        createPlayer = new BombermanButton("CREATE PLAYER", button_dimension);
        exit = new BombermanButton("EXIT",button_dimension);

        // 2. Add components
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(startGame);
        buttonPanel.add(selectPlayer);
        buttonPanel.add(createPlayer);
        buttonPanel.add(exit);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        add(backgroundPanel);

        setSize(screenSize.width, screenSize.height);
        setVisible(true);

    }

    private void setStartGameActionPerformed(ActionListener listener){ startGame.addActionListener(listener); }
    private void setSelectPlayerActionPerformed(ActionListener listener){ selectPlayer.addActionListener(listener); }
    private void setCreatePlayerActionPerformed(ActionListener listener){ createPlayer.addActionListener(listener); }
    private void setExitActionPerformed(ActionListener listener){ exit.addActionListener(listener); }

    @Override
    public void setActionsPerformed(MainMenuController controller) {
        setExitActionPerformed(controller.getExitAction());
        setSelectPlayerActionPerformed(controller.getSelectPlayerAction());
        setCreatePlayerActionPerformed(controller.getCreatePlayerAction());
        setStartGameActionPerformed(controller.getStartGameAction());
    }
}
