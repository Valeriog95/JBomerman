package Model.Game;

import Model.Game.Blocks.*;
import Model.Game.Rules.Difficulty;
import Model.Game.Rules.Status;
import Model.Game.Rules.Playiing;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Model.Game play.
 */
public class GamePlay extends Field implements Playiing {

    private List<Coordinate> bombs;
    private Status status;
    private Difficulty selected;
    private int lives = 3;
    private int points = 0;

    /**
     * Instantiates a new View.Game play.
     */
    public GamePlay() {}

    /**
     * Gets game status.
     *
     * @return the game status
     */
    public HashMap<String,String> getGameStatus() {
        return new HashMap<>(
                Map.of(
                    "status", status.toString(),
                    "lives" , Integer.toString(lives),
                    "bombs" , Integer.toString(bombNumber),
                    "power" , Integer.toString(bombsPower),
                    "points" , Integer.toString(points)
                )
        );
    }

    /**
     * check if all lifes are used
     */
    private void gameLost(){status = (--lives == 0) ? Status.END : Status.LOST;}

    /**
     * Gets points.
     *
     * @return the points
     */
    public int getPoints() { return points; }

    /**
     * Gets points.
     *
     * @param points game points
     */
    public void setPoints(int points) { this.points = points; }

    /**
     * Private method that move one monster at time
     *
     * @param monster monster to be moved
     * @return position changed on field
     */
    @Override
    public List<PositionChanged> moveMonster(Coordinate monster){

        List<PositionChanged> changes = new ArrayList<>();
        List<Coordinate> moves = new ArrayList<>(monster.getMoves());
        Monster m;
        try { m = (Monster) blocks.get( monster ); }
        catch (ClassCastException e) {return changes;}

        while(moves.size() > 0){

            // Generate random moves
            int randomIndex = random.nextInt(moves.size());
            Coordinate nextMove = moves.get(randomIndex);
            Block nextBlock = blocks.get(nextMove);

            moves.remove(randomIndex);

            // Check if next block is crossable
            if(nextBlock instanceof Crossable && !(nextBlock instanceof Monster)){

                // Cross block
                Block previous = m.getPrevious();
                m.setPrevious(nextBlock);
                status = ((Crossable) nextBlock).crossed(m);

                // Hit Player
                if (status == Status.LOST) { gameLost(); return changes;}

                // changes to be notified
                changes.add(new PositionChanged(nextMove.y(),nextMove.x(),m.toString()));
                changes.add(new PositionChanged(monster.y(),monster.x(),previous.toString()));

                // Creating new blocks
                blocks.put(monster,previous);
                blocks.put(nextMove,m);

                // Updating blank blocks
                blanks.remove(nextMove);
                if (previous instanceof Blank) blanks.add(monster);

                break;
            }
        }

        return changes;
    }

    /**
     * Method used to cyclic respawn monster
     * @return position changed on game field
     */
    public List<PositionChanged> respawnMonsters(){
        spawnMonsters(selected.getMonsters() - monsters.size());
        setPortal( false );
        return monsters.stream()
                .map(m -> PositionChanged.getByCoordinate(m, "Monster"))
                .collect(Collectors.toList());
    }

    /**
     * Gets bomb if available else Null
     *
     * @return the bomb coordinate
     */
    public Coordinate getBomb() {

        // Take care that two bombs can't have same place and bomb can't stay over Portal
        if (bombNumber == 0 || bombs.contains(player) || player.equals(portal) )
            return null;

        bombs.add(player);
        bombNumber--;
        ((Player)blocks.get(player)).setPrevious(new Bomb());
        return new Coordinate(player.y(), player.x());
    }

    /**
     * Move player in current position
     * @param moves the moves to be played
     * @return list of position changed on fields
     */
    @Override
    public List<PositionChanged> movePlayer(Moves moves) {

        // Find next moves by functional interface and checks next block
        Coordinate nextMove = moves.move().apply(player);
        Block crossingBlock = blocks.get(nextMove);
        List<PositionChanged> changes = new ArrayList<>();

        if (crossingBlock instanceof Crossable) {

            Player p = (Player) blocks.get(player);
            Block previous = p.getPrevious();
            p.setPrevious(crossingBlock);

            // get crossed block
            status = ((Crossable) crossingBlock).crossed(blocks.get(player));

            // Hit monster
            if (status == Status.LOST) { gameLost(); return changes;}

            if (crossingBlock instanceof PowerUp) {

                // Drop power Up
                switch (((PowerUp) crossingBlock).getType()) {
                    case Points50 -> points += 50;
                    case Points100 -> points += 100;
                    case AddBomb -> bombNumber += 1;
                    case AddLife -> lives += 1;
                    case Points2x -> points *= 2;
                    case Points3x -> points *= 3;
                    case Points5x -> points *= 5;
                    case ImprovePower -> bombsPower += 1;
                }
            }

            // add changes
            changes.add(new PositionChanged(player.y(), player.x(), previous.toString()));
            changes.add(new PositionChanged(nextMove.y(), nextMove.x(), p.toString()));

            // Set previous Block and get new ones
            blocks.put(nextMove,p);
            blocks.put(player,previous);

            // Update blank list
            if (previous instanceof Blank) blanks.add(player);
            blanks.remove(nextMove);

            // Move player coordinate
            player = nextMove;
        }

        return changes;

    }

    /**
     * These methods move whole monsters on field, take care that if one monster hit the player, game is lost,
     * if monster cross the power up, power up is lost
     *
     * @return whole position changed on field
     */
    public List<PositionChanged> moveMonsters() {

        List<PositionChanged> changes = new ArrayList<>();

        for(Coordinate monster : monsters)
            changes.addAll(moveMonster(monster));

        // now updating monster list after instruction above to avoid concurrentModificationException
        monsters.clear();
        changes.stream()
                .filter(ps -> ps.Type().equals("Monster"))
                .forEach(pos -> monsters.add(new Coordinate(pos.y(),pos.x())));

        if(monsters.size() == 0)
            setPortal(true);

        return changes;
    }

    /**
     * Method used when explode bomb
     * @param bomb the bomb coordinate
     * @return position changed on the field
     */
    @Override
    public List<PositionChanged> explodeBomb(Coordinate bomb) {

        List<PositionChanged> changes = new ArrayList<>();
        List<Moves> moveFnc = Arrays.asList(Moves.MOVE_DOWN,Moves.MOVE_UP,Moves.MOVE_LEFT,Moves.MOVE_RIGHT);

        // restore bombs settings
        bombs.remove(bomb);
        bombNumber++;

        // Explode selected bomb
        for (Moves fnc : moveFnc) {

            Coordinate nextMove = fnc.move().apply(bomb);

            for (int i = bombsPower; i > 0; i--) {

                Block destroyed = blocks.get(nextMove);

                // Check if next block is destroyable else exit loop
                if(destroyed instanceof Crossable || destroyed instanceof Destroyable) {

                    if(destroyed instanceof Destroyable){

                        // Remove if it is a monster, then activate portal if it's the last one
                        if(destroyed instanceof Monster)
                            monsters.remove(nextMove);
                        if (monsters.size() == 0)
                            setPortal( true );
                        // Destroy the next block
                        status = ((Destroyable) blocks.get(nextMove)).destroy();

                        // Get points
                        points += ((Destroyable) blocks.get(nextMove)).getPoints();

                        // END... View.Game Lost!
                        if (status == Status.LOST) { gameLost(); break; }
                    }

                    // Set explosion Block and set its block holded Place block spawned at this position
                    blocks.put(
                            nextMove,
                            new Explosion(
                                    Direction.getDirectionFromMove(fnc, i),
                                    blocks.get(nextMove) instanceof Destroyable ?
                                            ((Destroyable) blocks.get(nextMove)).spawned() : blocks.get(nextMove)
                            )
                    );

                    // Add position changed to list
                    changes.add(PositionChanged.getByCoordinate(nextMove,blocks.get(nextMove).toString()));

                    // moving up at the same direction
                    nextMove = fnc.move().apply(nextMove);

                } else break;
            }
        }

        // where bombs is exploded, the block is Blank if it's not a portal
        blocks.put(bomb,new Explosion(Direction.Center,new Blank()));
        changes.add(PositionChanged.getByCoordinate(bomb,blocks.get(bomb).toString()));

        return changes;
    }

    /**
     * Remove explosion spawned on game field
     * @return position changed on field
     */
    @Override
    public List<PositionChanged> removeExplosions() {

        return blocks.keySet().stream()
                .filter(k -> blocks.get(k) instanceof Explosion)
                .map(p -> {
                    blocks.put(p,((Explosion)blocks.get(p)).getHeld());
                    return PositionChanged.getByCoordinate(p,blocks.get(p).toString());
                })
                .toList();
    }

    /**
     * Start game
     * @param diff the difficulty, used to set blocks
     * @return Initial position of the blocks in the field, provided as a matrix of strings
     */
    @Override
    public List<List<String>> startGame(Difficulty diff) {
        status = Status.CONTINUE;
        selected = diff;
        bombs = new ArrayList<>();
        resetField();
        spawnMonsters(diff.getMonsters());
        spawnBricks(diff.getBricks(),diff.getPowerUps());
        return getFieldPositions();
    }
}
