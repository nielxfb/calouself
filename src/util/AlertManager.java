package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Utility class for managing and displaying different types of alerts.
 */
public class AlertManager {

    /**
     * Displays an error alert with the specified message.
     *
     * @param message the error message to display
     */
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays a success alert with the specified message.
     *
     * @param message the success message to display
     */
    public static void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Successfully done");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation alert with the specified message and waits for user response.
     *
     * @param message the confirmation message to display
     * @return true if the user confirms the action, false otherwise
     */
    public static Boolean showPopUp(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm your action");
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

}