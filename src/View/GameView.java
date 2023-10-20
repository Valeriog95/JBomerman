/* Projects : JBomberman
 * Created 22/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View;

import Controller.Game.GameController;
import Model.Game.PositionChanged;
import View.Game.Blocks.Block;
import View.Game.Blocks.Player;
import View.Game.Field;
import View.Menu.Generics.BombermanLabel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Game view class
 */
public class GameView extends JFrame implements BombermanView<GameController> {

    private int bombs; // number of bombs
    private int power; // bomb power
    private int lives; // number of lives
    private int points; // points

    private static final float STATS_WIDTH = 0.95F; // stats label width
    private static final float STATS_HEIGHT = 0.15F; // stats label height

    private final Field field; // Field widget
    private final BombermanLabel stats; // stats widget

    private String getStatStrings(){
        return String.format(
                "BombsAvailable : %d; BombsPower : %d; Lives : %d, Points : %d;",
                bombs,power,lives,points
        );
    }

    /**
     * Constructor of game view
     */
    public GameView(){

        // 1. Set as full screen
        setTitle("JBomberman Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. Set widget dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension stats_dimension = new Dimension(
                ((int) (screenSize.getWidth()*STATS_WIDTH)),((int) ((screenSize.getHeight())*STATS_HEIGHT))
        );
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.BLACK);
        backgroundPanel.setLayout(new BorderLayout());

        // 3. Create a stats view
        JPanel statsPanel = new JPanel();
        stats = new BombermanLabel(stats_dimension);
        stats.setText(getStatStrings());
        statsPanel.setBackground(Color.BLACK);
        statsPanel.add(stats);
        backgroundPanel.add(statsPanel,BorderLayout.NORTH);

        // 4. Create a field view
        Dimension field_dimension = new Dimension(
                Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height
        );
        field = new Field( field_dimension );
        backgroundPanel.add(field);

        add(backgroundPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Set player avatar colour
     * @param avatar colour of the avatar
     */
    public void setPlayerAvatar(String avatar){
        Player.setAvatar(avatar);
    }

    /**
     * Set stats label pass it as a map.
     * Keys allowed :
     * - bombs,
     * - lives,
     * - power,
     * - points
     * @param stat map of stats to be showed
     */
    public void setStats(HashMap<String,String> stat){
        if(stat.containsKey( "bombs" )) bombs = Integer.parseInt( stat.get( "bombs" ) );
        if(stat.containsKey( "lives" )) lives = Integer.parseInt( stat.get( "lives" ) );
        if(stat.containsKey( "power" )) power = Integer.parseInt( stat.get( "power" ) );
        if(stat.containsKey( "points" )) points = Integer.parseInt( stat.get( "points" ) );
        stats.setText(getStatStrings());
    }

    /**
     * Set position changed
     * @param positions parameter passed when update view as a generic type, but only these are managed :
     *                  - List of StringList List of PositionChanged
     */
    public void setPositions(Object positions){
        Object checked = positions;     // moke obj used to check type

        while(checked instanceof List) checked = ((List<?>) checked).get(0);

        if (checked instanceof String)
            field.paintBlocks( (List<List<String>>) positions );

        else if (checked instanceof PositionChanged)
            field.paintChanges((List<PositionChanged>) positions);
    }

    /**
     * Set current level of the view
     * @param level current level
     */
    public void setCurrentLevel(int level){
        Block.setLevel( level );}

    /**
     * Kill current player
     */
    public void killPlayer() {
        Player.setDead();
        try { Thread.sleep( 2000 ); } catch (InterruptedException e) { throw new RuntimeException( e );}
        Player.setAlive();
    }

    @Override
    public void setActionsPerformed(GameController controller) { /* TODO */   }

}
