package Model.Menu;

import java.io.IOException;
import java.util.List;

/**
 * User manager interface
 */
public interface UserManager {

    /**
     * Get list of users
     * @return list of users
     * @throws IOException when file are not opened
     */
    List<User> getUsers() throws IOException;

    /**
     * Store current user settings
     * @param user user to be saved
     */
    void saveUser(User user);
}
