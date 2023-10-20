package Model.Menu;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * User type
 */
public class User implements Serializable {
    private final String nickname;
    private final String avatar;
    private final Stats stats;
    private Level level;

    /**
     * Base constructor of user
     * @param nickname nickname of the user
     * @param avatar avatar selected
     */
    public User(String nickname, String avatar) {
        this.nickname = nickname;
        this.avatar = avatar;
        stats = new Stats();
        this.level = Level.TRAINING_GROUNDS;
    }

    /**
     * A bit more complex constructor, provide also the stats of player
     * @param nickname nickname of the user
     * @param avatar avatar selected
     * @param stats player stats
     */
    public User(String nickname, String avatar, Stats stats) {
        this.nickname = nickname;
        this.avatar = avatar;
        this.stats = stats;
        this.level = Level.fromScore(stats.getAllScores());
    }

    /**
     * Get level reached
     * @return level reached
     */
    public String getLevel() { return level.name(); }

    /**
     * Get Nickname
     * @return the nickname
     */
    public String getNickname() { return nickname; }

    /**
     * Get user avatar
     * @return the avatar
     */
    public String getAvatar() { return avatar; }

    /**
     * Get player stats
     * @return the stats of the player
     */
    public Stats getStats() { return stats; }

    /**
     * Add game won
     * @param score score reached
     */
    public void won(int score){
        stats.addWon(score);
        level = Level.fromScore(stats.getAllScores());
    }

    /**
     * Add game lost
     * @param score score reached
     */
    public void lost(int score){
        stats.addLost(score);
        level = Level.fromScore(stats.getAllScores());
    }

    /**
     * Return user as a json object
     * @return json object of the user
     */
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("nickname",nickname);
        json.put("avatar",avatar);
        json.put("stats",stats.toJson());
        return json;
    }

}
