/* Projects : JBomberman
 * Created 09/10/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller.Sounds;

/**
 * Class Audio manager, manage Bomberman audio samples
 */
public class AudioManager {
    private static final String path = "data/musics/";
    private final AudioPlayer audioPlayer;

    /**
     * Constructor of Audio Manager
     */
    public AudioManager() {
        audioPlayer = AudioPlayer.getInstance();
    }

    /**
     * Bomberman audio samples player
     * @param track track to be played
     */
    public void play(BombermanAudioSample track){
        audioPlayer.stop();
        switch (track) {
            case BOMB_EXPLODES -> audioPlayer.play( path + "BombExplodes.wav" );
            case GAME_WINNER -> audioPlayer.play( path + "Game Winner.wav" );
            case MAIN_MENU -> audioPlayer.play( path + "Main Menu.wav" );
            case MATCH_WINNER -> audioPlayer.play( path + "Match Winner.wav" );
            case MONSTER_DIES -> audioPlayer.play( path + "MonsterDies.wav" );
            case PAUSE -> audioPlayer.play( path + "Pause.wav" );
            case PLACE_BOMB -> audioPlayer.play( path + "Place Bomb.wav" );
            case PLAYER_DIES -> audioPlayer.play( path + "PlayerDies.wav" );
            case POWER_UP_GET -> audioPlayer.play( path + "PowerUpGet.wav" );
            case RESULT_SCREEN -> audioPlayer.play( path + "Result Screen.wav" );
            case STAGE_CLEAR -> audioPlayer.play( path + "Stage Clear.wav" );
            case STAGE_INTRO -> audioPlayer.play( path + "Stage Intro.wav" );
        }
    }

    /**
     * Close audio manager
     */
    public void close(){
        audioPlayer.stop();
    }
}
