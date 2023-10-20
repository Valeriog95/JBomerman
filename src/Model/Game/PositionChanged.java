package Model.Game;

/**
 * Utility class used for notify the view of which position change on the screen
 * @param y coordinate y
 * @param x coordinate x
 * @param Type block type
 */
public record PositionChanged(int y, int x, String Type) {

    /**
     * Utility method used to get a posion change object from coordinate object and string type
     * @param c Coordinate
     * @param type block type
     * @return position changed instance
     */
    public static PositionChanged getByCoordinate(Coordinate c, String type){
        return new PositionChanged(c.y(), c.x(), type);
    }
}
