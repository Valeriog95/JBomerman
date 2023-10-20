/* Projects : JBomberman
 * Created 23/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View;

/**
 * Exception raised when no images are founded
 */
public class ImageNotFoundException extends RuntimeException{

    /**
     * Constructor of exceptio
     * @param message message to be showed
     */
    public ImageNotFoundException(String message) {
        super(message);
    }
}
