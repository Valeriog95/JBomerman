package Model.Menu;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of factory pattern of the users
 */
public class UserFactory implements UserManager {
    private final String filePath;
    List<User> users;

    /**
     * Constroctor of PlayerFactory class, pass path of file handled
     * @param filePath String of absolute path of json file
     */
    public UserFactory(String filePath) {
        this.filePath = filePath;
        users = new ArrayList<>(getUsers());
    }

    /**
     * Utility function that create an empty json file
     */
    private void createEmptyJsonFile(){
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error occurred while creating file: " + e.getMessage());
        }
    }

    /**
     * Utility function that write list of players to file
     */
    private void writePlayersToFile(){


        Map<String, Map<String, Object>> usersMap = new HashMap<>();
        users.forEach(user -> usersMap.put(user.getNickname(),Map.of(
                "avatar", user.getAvatar(),
                "won", user.getStats().toJson().get("won"),
                "lost", user.getStats().toJson().get("lost")
        )));

        JSONObject us = new JSONObject(usersMap);


        // Write JSON object to file
        FileWriter fileWriter = null;

        File file = new File(filePath);

        if(!file.exists())
            createEmptyJsonFile();

        try {
            fileWriter = new FileWriter(filePath);
        fileWriter.write(us.toString());
        fileWriter.flush();
        fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getUsers(){

        // If file was already readed, return the list
        if (users != null) return users;

        // Check if file exists else create a new one
        try {
            JSONObject obj = new JSONObject(new String(Files.readAllBytes(Path.of(filePath))));
            users = obj.toMap()
                    .keySet()
                    .stream()
                    .map(k -> {
                        HashMap<String, Object> current = (HashMap<String, Object>) obj.toMap().get(k);
                        return new User(
                                k,
                                (String) current.get("avatar"),
                                new Stats(
                                        (List<Integer>) current.get("won"),
                                        (List<Integer>) current.get("lost")
                                )
                        );
                    })
                    .toList();

        } catch (Exception e){
           createEmptyJsonFile();
        }

        return users;

    }

    @Override
    public void saveUser(User user) {
        users.add(user);
        writePlayersToFile();
    }
}
