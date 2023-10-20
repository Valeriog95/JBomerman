package Model.Menu;

import org.junit.jupiter.api.BeforeAll;

import java.util.List;

class UserManagerFactoryTest {

    private static final String writePath = "C:\\Users\\Admin\\Documents\\Projects\\Software\\JBomberman\\data\\records\\Players.json";
    private static final String readPath = "C:\\Users\\Admin\\Documents\\Projects\\Software\\JBomberman\\data\\records\\TestPlayers.json";


    @BeforeAll
    static void createFactory(){
        Stats valerioStats = new Stats(List.of(1234,4321),List.of(1234,4321));
        User valerio = new User("Valerio","avatar_0",valerioStats);
        UserFactory Saver = new UserFactory(writePath);
        UserFactory Reader = new UserFactory(readPath);
        Reader.getUsers().forEach(Saver::saveUser);

    }

    @org.junit.jupiter.api.Test
    void getUsers() {
    }
}