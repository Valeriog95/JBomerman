package View.Game;

import Model.Game.Coordinate;
import Model.Game.PositionChanged;
import View.Game.Blocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Field view class
 */
public class Field extends JPanel {

    private final List<List<Block>> blocks;

    private void imageInits(){
        Blank.setImages(); Bomb.setImages(); Blank.setImages();
        Bomb.setImages(); Brick.setImages(); Portal.setImages();
        PowerUpAddBomb.setImages(); PowerUpAddLife.setImages();PowerUpImprovePower.setImages();PowerUpPoints2x.setImages();
        PowerUpPoints3x.setImages();PowerUpPoints5x.setImages();
        PowerUpPoints50.setImages();
        PowerUpPoints100.setImages();
        ExplosionUp.setImages();ExplosionDown.setImages();ExplosionRight.setImages();ExplosionLeft.setImages();ExplosionCenter.setImages();
        ExplosionContinueUp.setImages();ExplosionContinueDown.setImages();ExplosionContinueRight.setImages();ExplosionContinueLeft.setImages();
        Monster.setImages(); Player.setImages();Wall.setImages();
    }

    private void resetBlocks() {
        if(blocks.size() > 0)
            for(List<Block> row : blocks)
                for(Block block : row)
                    remove( block );
    }

    private Dimension calculateBlockDimension(){
        return new Dimension(
                getWidth() / Coordinate.MAX_X,
                getHeight() / Coordinate.MAX_Y
        );
    }

    private Block createBlock(String type,int y, int x){
        try {
            Block block = (Block) Class.forName("View.Game.Blocks." + type).getConstructor().newInstance();

            if(block instanceof Player) {
                ((Player) block).setCurrentPosition(new Coordinate(y, x));
            }
            return block;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Paint blocks on screen, require a matrix of block as matrix of strings
     * @param positions positions block matrix
     */
    public void paintBlocks(List<List<String>> positions) {

        resetBlocks();

        for(int y = 0; y < positions.size(); y++){

            List<Block> row = new ArrayList<>();

            for(int x = 0; x < positions.get(y).size(); x++) {
                Block block = createBlock(positions.get(y).get(x), y, x);
                row.add(block);
                add(block,(y*Coordinate.MAX_X)+x);
            }
            blocks.add(row);
        }
    }

    /**
     * Paint changes on screen
     * @param positions list of changes
     */
    public void paintChanges(List<PositionChanged> positions){
        for(PositionChanged pos : positions) {
            Block block = createBlock(pos.Type(), pos.y(), pos.x());
            remove((pos.y()*Coordinate.MAX_X)+pos.x());
            add(block,(pos.y()*Coordinate.MAX_X)+pos.x());
            blocks.get(pos.y()).set(pos.x(), block);
            revalidate();
        }
    }

    /**
     * Constructor of the field, create a new one
     * @param dimension dimension of field panels
     */
    public Field(Dimension dimension){

        // 1. Instantiate super Object and allocate memory
        super(new GridLayout(Coordinate.MAX_Y,Coordinate.MAX_X));
        setSize(dimension);
        setVisible(true);

        // 2.Set dimension for whole blocks
        blocks = new ArrayList<>();
        Block.setDimension(calculateBlockDimension());

        // 3. Buffering whole images
        imageInits();

    }



}
