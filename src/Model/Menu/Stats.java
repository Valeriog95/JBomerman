package Model.Menu;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Stats.
 *
 * @author Valerio Gregori Created on 16.08.2023
 */
public class Stats implements java.io.Serializable {
    private List<Integer> won;
    private List<Integer> lost;

    /**
     * Instantiates a new Stats.
     */
    public Stats() {
        won = new ArrayList<>();
        lost = new ArrayList<>();
    }

    /**
     * Constructor of stats by values
     * @param values stats values as a map
     */
    public Stats(HashMap<String,List<Integer>> values) {
        won = values.containsKey("won") ? values.get("won") : new ArrayList<>();
        lost = values.containsKey("lost") ? values.get("lost") : new ArrayList<>();
    }

    /**
     * Instantiates a new Stats.
     *
     * @param won  the won
     * @param lost the lost
     */
    public Stats(List<Integer> won, List<Integer> lost) {
        this.won = won;
        this.lost = lost;
    }

    /**
     * Get won int.
     *
     * @return the int
     */
    public int getWon(){ return won.size();}

    /**
     * Get lost int.
     *
     * @return the int
     */
    public int getLost(){ return lost.size();}

    /**
     * set won games
     * @param won list of won games
     */
    public void setWon(List<Integer> won) { this.won = won; }

    /**
     * Set lost games
     * @param lost list of lost games
     */
    public void setLost(List<Integer> lost) { this.lost = lost; }

    /**
     * Gets won scores.
     *
     * @return the won scores
     */
    public int getWonScores() {return won.stream().mapToInt(Integer::intValue).sum();}

    /**
     * Gets lost scores.
     *
     * @return the lost scores
     */
    public int getLostScores() {return lost.stream().mapToInt(Integer::intValue).sum();}

    /**
     * Gets played.
     *
     * @return the played
     */
    public int getPlayed() {return getWon() + getLost();}

    /**
     * Gets avg scores.
     *
     * @return the avg scores
     */
    public float getAvgScores() {return (float)(getAllScores() / getPlayed());}

    /**
     * Add won.
     *
     * @param score the score
     */
    public void addWon(int score) {won.add(score);}

    /**
     * Add lost.
     *
     * @param score the score
     */
    public void addLost(int score) {lost.add(score);}


    /**
     * Gets all scores.
     *
     * @return the all scores
     */
    public int getAllScores() { return getLostScores()+getWonScores(); }

    /**
     * get this as a json object
     * @return json object of stats
     */
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("won",won);
        json.put("lost",lost);
        return json;
    }
}
