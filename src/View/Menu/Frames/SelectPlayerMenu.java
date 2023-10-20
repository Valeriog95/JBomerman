/* Projects : JBomberman
 * Created 26/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Menu.Frames;

import Controller.Menu.SelectPlayerController;
import Model.Menu.User;
import View.BombermanView;
import View.Menu.Generics.BombermanButton;
import View.Menu.Generics.BombermanMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Arrays;
import java.util.List;

/**
 * Select player menu window
 */
public class SelectPlayerMenu extends BombermanMenu implements BombermanView<SelectPlayerController> {

    private static final float USER_BUTTON_HEIGHT = 0.05F; // User butt
    private static final float USER_BUTTON_WIDTH = 0.95F;

    private JPanel userPanel; // User panel

    private static final Dimension buttonDimension = new Dimension(
            (int)(Toolkit.getDefaultToolkit().getScreenSize().width * USER_BUTTON_WIDTH),
            (int)(Toolkit.getDefaultToolkit().getScreenSize().height * USER_BUTTON_HEIGHT)
    );

    /**
     * Constructor of select player menu
     */
    public SelectPlayerMenu() {
        super();
        setSize(screenSize.width, screenSize.height);
        setVisible(true);
    }

    /**
     * Set a list of user created and saved in data/records/Players.json
     * @param users list of users
     */
    public void setUsers(List<User> users) {

        remove(backgroundPanel);

        userPanel = new JPanel(new GridLayout(users.size() + 1,1));
        userPanel.setBackground(Color.BLACK);

        for(User u : users){

            // Create the string to add to Label
            String userInfo =
                u.getNickname() + (" ".repeat((16 - u.getNickname().length()))) +
                u.getAvatar() + (" ".repeat((12 - u.getAvatar().length()))) +
                "WON : " + u.getStats().getWon() +
                        (" ".repeat((12 - Integer.toString(u.getStats().getWon()).length()))) +
                "LOST : " + u.getStats().getLost() +
                        (" ".repeat((12 - Integer.toString(u.getStats().getLost()).length()))) +
                "PLAYED : " + u.getStats().getPlayed() +
                        (" ".repeat((12 - Integer.toString(u.getStats().getPlayed()).length()))) +
                    u.getLevel();

            // then add it to the screen and add action listener
            BombermanButton user = new BombermanButton(userInfo, buttonDimension);
            userPanel.add(user,BorderLayout.CENTER);
        }

        backgroundPanel.add(userPanel,BorderLayout.CENTER);
        backgroundPanel.setBounds(
                (int) ((screenSize.getWidth() - menuImage.getWidth()) / 2),
                backgroundPanel.getY(),
                backgroundPanel.getWidth(),
                backgroundPanel.getHeight()
        );
        add(backgroundPanel);
        revalidate();
        repaint();
    }


    @Override
    public void setActionsPerformed(SelectPlayerController controller) {

        Arrays.stream(userPanel.getComponents()).forEach(
            button -> ((BombermanButton) button).addActionListener(
                            controller.getSelectPlayerAction(
                                    ((BombermanButton) button).getText().split(" ",1)[0]
                            )
            )
        );
    }
}
