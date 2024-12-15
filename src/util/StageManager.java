package util;

import javafx.stage.Stage;

/**
 * Utility class for managing the primary stage of a JavaFX application.
 */
public class StageManager {

    private static StageManager instance;
    private Stage st;

    /**
     * Private constructor to initialize the StageManager with the given stage.
     *
     * @param st the primary stage of the JavaFX application
     */
    private StageManager(Stage st) {
        this.st = st;
    }

    /**
     * Provides a synchronized instance of the StageManager class.
     *
     * @param st the primary stage of the JavaFX application
     * @return a singleton instance of the StageManager class
     */
    public static StageManager getInstance(Stage st) {
        if (instance == null) {
            instance = new StageManager(st);
        }
        return instance;
    }

    /**
     * Gets the primary stage of the JavaFX application.
     *
     * @return the primary stage
     */
    public Stage getStage() {
        return st;
    }

}