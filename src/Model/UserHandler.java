package Model;

import Model.Menu.UserFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User handler
 */
public class UserHandler {

    private static final String DB_FILE_PATH = "data/records/Players.json";

    private final UserFactory userHandler;
    private final List<Model.Menu.User> users;
    private int currentUserIndex;

    /* Singleton pattern stuffs */
    static private UserHandler instance;
    private UserHandler() {
        userHandler = new UserFactory(DB_FILE_PATH);
        users = new ArrayList<>(userHandler.getUsers());
    }

    /**
     * Get static class instance
     * @return user instance
     */
    public static UserHandler getInstance() { return instance == null ? instance = new UserHandler() : instance; }

    /**
     * Store current play stats
     */
    private void storeStats(){ users.forEach(userHandler::saveUser); }

    /**
     * Add a win game to current user
     * @param score score reached
     */
    public void win(int score){ users.get(currentUserIndex).won(score); storeStats(); }

    /**
     * Add a lost game to current user
     * @param score score reached
     */
    public void lost(int score){ users.get(currentUserIndex).lost(score); storeStats(); }

    /**
     * Get list of whole users
     * @return users list
     */
    public List<Model.Menu.User> getUsers() {
        return users;
    }

    /**
     * Get current user
     * @return the current user
     */
    public Model.Menu.User getCurrentUser() {
        return users.get(currentUserIndex);
    }

    /**
     * Setting up current user
     * @param userName name of current user
     */
    public void setCurrentUser(String userName){

        for (currentUserIndex = 0; currentUserIndex < users.size(); currentUserIndex++)
            if(users.get(currentUserIndex).getNickname().equals(userName))
                break;

        if (currentUserIndex == users.size()) currentUserIndex = 0;
    }

    /**
     * Add new user to the list, and to the database
     * @param name user name
     * @param avatar avatar of the user
     * @return true if user doesn't already exist
     */
    public boolean addNewUser(String name,String avatar) {

        if (users.stream().map(Model.Menu.User::getNickname).toList().contains(avatar))
            return false;

        users.add(new Model.Menu.User(name,avatar));
        currentUserIndex = users.size() - 1;
        storeStats();
        return true;

    }

}
