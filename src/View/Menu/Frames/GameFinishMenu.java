/* Projects : JBomberman
 * Created 02/10/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Frames;

import Controller.Game.GameFinishController;
import Model.Menu.User;
import View.BombermanView;
import View.Menu.Generics.BombermanButton;
import View.Menu.Generics.BombermanLabel;
import View.Menu.Generics.BombermanMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Game finish window
 */
public class GameFinishMenu extends BombermanMenu implements BombermanView<GameFinishController> {

    private final BombermanButton finish;

    /**
     * Constructo of game finished window
     * @param result result of current gum
     * @param actualPoints point actually reached
     * @param totalPoints total points reached
     * @param level current level
     */
    public GameFinishMenu(String result,int actualPoints,int totalPoints, String level) {
        super();

        // 1. Create widgets
        BombermanLabel userName = new BombermanLabel(menuImageDimension());
        BombermanLabel actualStats = new BombermanLabel(menuImageDimension());
        BombermanLabel totalStats = new BombermanLabel(menuImageDimension());
        BombermanLabel levelReached = new BombermanLabel(menuImageDimension());
        finish = new BombermanButton("RETURN TO MAIN MENU'", getStandardButtonDimension());

        // 2. Set Text
        userName.setText(result);
        actualStats.setText("Game points : " + actualPoints);
        totalStats.setText("Total point : " + totalPoints);
        levelReached.setText("Congratulation, you are now a " + level);

        // 3. Create panel container
        JPanel creatorPanel = new JPanel();
        creatorPanel.setLayout(new BoxLayout(creatorPanel,BoxLayout.Y_AXIS));
        creatorPanel.setBackground(Color.BLACK);
        List.of(userName,actualStats,totalStats,levelReached,finish)
                .forEach(creatorPanel::add);
        backgroundPanel.add(creatorPanel);
        add(backgroundPanel);

        // 4. Create window
        setSize(screenSize.width, screenSize.height);
        setVisible(true);
    }

    private void setFinishActionPerformed(ActionListener listener){
        finish.addActionListener(listener);
    }

    @Override
    public void setActionsPerformed(GameFinishController controller) {
        setFinishActionPerformed(controller.getFinishAction());
    }
}
