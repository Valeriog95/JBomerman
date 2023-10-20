package Model.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * record Type Coordinate.
 *
 * @param y the y
 * @param x the x
 */
public record Coordinate(int y, int x) {

    public static final int MAX_Y = 11;
    public static final int MAX_X = 23;

    /**
     * Get direction from one coordinate to another one
     * @param from coordinate start
     * @param to coordinate end
     * @return direction in string
     */
    public static String getDirection(Coordinate from, Coordinate to){
        if (to.x() != from.x())     return to.x() > from.x() ? "RIGHT" : "LEFT";
        if (to.y() != from.y)       return to.y() > from.y() ? "DOWN" : "UP";
        return "CENTER";
    }

    @Override
    public int hashCode() { return Objects.hash(y,x); }

    @Override
    public boolean equals(Object obj) {

        // Preliminar checks
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        // Controls
        Coordinate o = (Coordinate) obj;
        return x == o.x() && y == o.y();
    }

    /**
     * Move up coordinate.
     *
     * @return the coordinate
     */
    public Coordinate moveUp(){ return y > 0  ?  new Coordinate(y - 1, x) : null; }

    /**
     * Move down coordinate.
     *
     * @return the coordinate
     */
    public Coordinate moveDown(){ return y < MAX_Y ?  new Coordinate(y + 1, x) : null; }

    /**
     * Move right coordinate.
     *
     * @return the coordinate
     */
    public Coordinate moveRight(){ return x < MAX_X ?  new Coordinate(y , x + 1) : null; }

    /**
     * Move left coordinate.
     *
     * @return the coordinate
     */
    public Coordinate moveLeft(){ return x > 0 ?  new Coordinate(y , x - 1) : null; }

    /**
     * Get moves list.
     *
     * @return the list
     */
    public List<Coordinate> getMoves(){

        Coordinate[] moves = {moveRight(),moveDown(),moveLeft(),moveUp()};
        return Arrays.stream(moves).filter(Objects::nonNull).toList();
    }


    /**
     * Find max distance from two point recursively
     *
     * @param end  the end
     * @param field the field
     * @return the int
     */
    public int findDistance(Coordinate end, List<Coordinate> field){ return findDistance(end,field,0); }

    private int findDistance(Coordinate end, List<Coordinate> field, int distance){

        /* Base cases */
        // arrived
        if(this.equals(end))
            return distance;

        // Create a list of following moves, considering only the joint sets, i.e., in this case,
        // those of the same sign; if the target has a greater abscissa or ordinate than the starting point,
        // consider only the greater ones and vice versa. Return -1 if no moves are available
        if (!field.contains(end)) field.add(end);
        List<Coordinate> next_moves = new ArrayList<>(getMoves().stream().filter(field::contains).toList());

        // Create a new field without unnecessary positions
        List<Coordinate> _field = new ArrayList<>(field);
        _field.removeAll(next_moves);

        // Orienting the graphs
        if (x > end.x()) next_moves.removeIf(t -> t.x() > x);
        else next_moves.removeIf(t -> t.x() < x);
        if (y > end.y()) next_moves.removeIf(t -> t.y() > y);
        else next_moves.removeIf(t -> t.y() < y);

        // No one match
        if (next_moves.size() == 0) return -1;

        /* Inductive cases */
        // Partial distance
        int partial;
        int _distance = -1 ;

        // Iterate whole fields
        for (Coordinate move : next_moves){

            // Check for next move
            partial = move.findDistance(end,_field,distance + 1);

            if(partial != -1 ) {
                if (_distance == -1 || _distance < partial)
                    _distance = partial;
            }
        }
        return _distance;
    }

}
