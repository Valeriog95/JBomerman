/* Projects : JBomberman
 * Created 09/10/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller.Sounds;

/**
 * Enum providing audio samples that can be played in a Bomberman game,
 * with expressive and descriptive names for each sample.
 */
public enum BombermanAudioSample {
    BOMB_EXPLODES,  // sound for bomb explosion
    GAME_WINNER,    // sound for winning the game
    MAIN_MENU,      // sound for the main menu
    MATCH_WINNER,   // sound for winning a match
    MONSTER_DIES,   // sound for a monster/enemy dying
    PAUSE,          // sound for pausing the game
    PLACE_BOMB,     // sound for placing a bomb
    PLAYER_DIES,    // sound for the player dying
    POWER_UP_GET,   // sound for getting a power-up
    RESULT_SCREEN,  // sound for the result screen
    STAGE_CLEAR,    // sound for clearing a stage
    STAGE_INTRO,    // sound for the stage introduction
    WALKING;        // sound for player or monster walking
}

