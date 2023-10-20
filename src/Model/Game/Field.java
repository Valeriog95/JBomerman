package Model.Game;

import Model.Game.Blocks.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Game Field class, provides only game grid
 */
public class Field {

    /* Constants used for initialize blocks/field*/
    private static final int INITIAL_BOMBS_NUMBER = 1;
    private static final int INITIAL_BOMBS_POWER = 1;
    private static final int MIN_DISTANCE_FROM_PLAYER = 5;

    /** Same random seed for generating moves blocks */
    protected static final Random random = new Random();

    /** Block Collections */
    protected Map<Coordinate, Block> blocks ;
    /** list of blank blocks */
    protected List<Coordinate> blanks;
    /** Player coordinate */
    protected Coordinate player;
    /** Portal coordinate */
    protected Coordinate portal ;
    /** Monster coordinate */
    protected List<Coordinate> monsters;
    /** Number of bombs */
    protected int bombNumber;
    /** Power of bombs*/
    protected int bombsPower;

    /**
     * Constructor of field
     */
    public Field() {
        blocks = new HashMap<>();
        blanks = new ArrayList<>();
        monsters = new ArrayList<>();
    }

    /**
     * Generate a standard field without monsters,bricks,portal and set player at his own position
     */
    protected void generateField(){

        /* Create edges */

        // First and last ROW
        for (int i = 0; i < Coordinate.MAX_X; i++){
            Coordinate upper = new Coordinate(0,i);
            Coordinate lower = new Coordinate(Coordinate.MAX_Y - 1,i);
            blocks.put(upper,new Wall());   blocks.put(lower,new Wall());
        }

        // First and last COLUMN
        for (int i = 0; i < Coordinate.MAX_Y; i++){
            Coordinate lefter = new Coordinate(i,0);
            Coordinate righter = new Coordinate(i,Coordinate.MAX_X - 1);
            blocks.put(lefter,new Wall());
            blocks.put(righter,new Wall());
        }

        /* Create central Walls */
        for (int i = 1; i < Coordinate.MAX_Y - 1; i++){

            for (int j = 1; j < Coordinate.MAX_X - 1; j++){

                // Blank rows
                Coordinate coordinates = new Coordinate(i,j);
                if(i%2 != 0){
                    blocks.put(coordinates,new Blank());
                    blanks.add(coordinates);
                }

                // Alternative columns
                else {

                    // Blank columns
                    if(j%2 != 0){
                        blocks.put(coordinates,new Blank());
                        blanks.add(coordinates);
                    }

                    // Alternative walls and blanks
                    else{
                        blocks.put(coordinates,new Wall());
                    }
                }
            }
        }

        /* Set player Coordinates */
        player = new Coordinate(9,1);
        blocks.put(player,new Player());
    }

    /**
     * Reset field parameters
     */
    protected void resetField(){
        player = null; portal = null;
        monsters.clear(); blanks.clear();  blocks.clear();
        generateField();
        bombNumber = Field.INITIAL_BOMBS_NUMBER;
        bombsPower = Field.INITIAL_BOMBS_POWER;
    }

    /**
     * Utility block generation Function using reflection
     * @param n number of block
     * @param type type of Block
     */
    private List<Coordinate> spawnBlocks(int n, String type) {

        // Save actual coordinates
        List<Coordinate> blockCoordinates = new ArrayList<>();

        // Check if there are too many Blocks
        if (n < (blanks.size() / 4)) {

            // Generate all blocks
            while (n != 0) {

                // Get random index
                int index = Field.random.nextInt(blanks.size());
                Coordinate coordinate = new Coordinate(blanks.get(index).y(), blanks.get(index).x());

                // Check if block generated is too near to Player
                if (
                        coordinate.findDistance(
                                player,
                                new ArrayList<>(
                                        blocks.keySet()
                                                .stream()
                                                .filter(x -> blocks.get(x) instanceof Blank)
                                                .toList()
                                )
                        ) < Field.MIN_DISTANCE_FROM_PLAYER)
                    continue;

                // Spawn Block,
                try {
                    blocks.put(coordinate,(Block) Class.forName(type).getConstructor().newInstance());
                } catch (Exception e) { e.printStackTrace(); }

                blockCoordinates.add(coordinate);

                // Remove blank space
                blanks.remove(coordinate);
                n--;
            }
        }

        return blockCoordinates;
    }

    /**
     * Spawn monsters.
     *
     * @param monster the number of monster to spawn
     */
    protected void spawnMonsters(int monster){
        monsters.addAll(spawnBlocks(monster,"Model.Game.Blocks.Monster"));
    }

    /**
     * Spawn bricks. Bricks must be at least one more than power ups
     *
     * @param brick   the number of bricks to spawn
     * @param powerUp the number of power up to spawn
     */
    protected void spawnBricks(int brick,int powerUp){

        // Input data invalid
        if(brick == 0 || powerUp >= brick) return;

        // Spawn Bricks
        List<Coordinate> newBrickPos = spawnBlocks(brick,"Model.Game.Blocks.Brick");

        // Convert some of them into Power Ups
        while (powerUp > 0 ){

            int index = Field.random.nextInt(newBrickPos.size());
            Coordinate coordinates = new Coordinate(newBrickPos.get(index).y(), newBrickPos.get(index).x());

            // Cast Brick then set Power Up
            ((Brick) blocks.get(coordinates)).setPowerUp(true);
            newBrickPos.remove(coordinates);

            powerUp--;
        }

        // Generate Portal if not exists
        if(portal == null){
            int index = Field.random.nextInt(newBrickPos.size());
            portal = new Coordinate(newBrickPos.get(index).y(), newBrickPos.get(index).x());
            ((Brick)blocks.get(portal)).setPortal(true);
        }

    }

    /**
     * Utility function that recreate portal if no one exists
     */
    protected void setPortal(boolean status) {

        try {
            ((Portal) blocks.get( portal )).setActive( status );
        }
        catch (ClassCastException e) {

            if (!(blocks.get(portal) instanceof Brick)) {
                List<Coordinate> blankBlocks = blocks.keySet().stream().filter( c -> blocks.get( c ) instanceof Brick ).toList();
                Coordinate converted;
                if (blankBlocks.size() == 0) {
                    converted = blanks.get( random.nextInt( blanks.size() ) );
                    blanks.remove( converted );
                } else
                    converted = blankBlocks.get( random.nextInt( blankBlocks.size() ) );

                blocks.put( converted, new Portal() );
                ((Portal) blocks.get( converted )).setActive( status );
            }
        }
    }

    /**
     * Get whole field position
     * @return a matrix of block names (as strings)
     */
    public List<List<String>> getFieldPositions() {

        // First reorder List into matrix
        List<List<Coordinate>> rows = new ArrayList<>();
        for(int i = 0; i < Coordinate.MAX_Y; i++){
            int finalI = i;
            rows.add(
                    blocks.keySet()
                            .stream()
                            .filter(c -> c.y() == finalI)
                            .sorted(Comparator.comparingInt(Coordinate::x))
                            .collect(toList())
            );
        }

        // return list of string objects
        return rows.stream()
                .map(l -> l.stream()
                        .map(c -> blocks.get(c).getClass().getSimpleName())
                        .collect(toList()))
                .collect(toList());
    }

}
