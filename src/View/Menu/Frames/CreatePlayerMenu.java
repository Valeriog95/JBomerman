/* Projects : JBomberman
 * Created 26/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Frames;

import Controller.Menu.CreatePlayerController;
import View.BombermanView;
import View.Menu.Generics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Create player window
 */
public class CreatePlayerMenu extends BombermanMenu implements BombermanWidgetAttributes, BombermanView<CreatePlayerController> {

    /* Buttons */
    private final BombermanButton red;
    private final BombermanButton white;
    private final BombermanButton blue;
    private final BombermanButton black;
    private final BombermanTextField userEntry;

    /**
     * Constructor of create player window
     */
    public CreatePlayerMenu() {

        super();
        Dimension button_dimension = getStandardButtonDimension();


        BombermanLabel infoLabel = new BombermanLabel(menuImageDimension());
        userEntry = new BombermanTextField(menuImageDimension());
        red = new BombermanButton("Red", button_dimension);
        white = new BombermanButton("White", button_dimension);
        blue = new BombermanButton("Blue", button_dimension);
        black = new BombermanButton("Black", button_dimension);

        infoLabel.setText("Nickname : ");

        JPanel creatorPanel = new JPanel();
        creatorPanel.setLayout(new FlowLayout());
        creatorPanel.setBackground(Color.BLACK);
        List.of(infoLabel,userEntry,red,white,blue,black).forEach(creatorPanel::add);

        backgroundPanel.add(creatorPanel, BorderLayout.CENTER);
        add(backgroundPanel);

        setSize(screenSize.width, screenSize.height);
        setVisible(true);
    }

    private void setRedActionPerformed(ActionListener listener){ red.addActionListener(listener); }
    private void setBlueActionPerformed(ActionListener listener){ blue.addActionListener(listener); }
    private void setWhiteActionPerformed(ActionListener listener){ white.addActionListener(listener); }
    private void setBlackActionPerformed(ActionListener listener){ black.addActionListener(listener); }

    /**
     * Get nickname wrote by user
     * @return nickname chose
     */
    public String getNickname(){ return userEntry.getText(); }

    @Override
    public void setActionsPerformed(CreatePlayerController controller){
        setRedActionPerformed(controller.getCreateRedPlayerAction());
        setWhiteActionPerformed(controller.getCreateWhitePlayerAction());
        setBlackActionPerformed(controller.getCreateBlackPlayerAction());
        setBlueActionPerformed(controller.getCreateBluePlayerAction());
    }
}
