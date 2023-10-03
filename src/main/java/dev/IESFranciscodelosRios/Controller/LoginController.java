package dev.IESFranciscodelosRios.Controller;

import dev.IESFranciscodelosRios.Domain.DAO.UserDAO;
import dev.IESFranciscodelosRios.Domain.Model.User;
import dev.IESFranciscodelosRios.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class LoginController {

    @FXML
    private TextField nickname_input;

    @FXML
    private Button loginBTN;

    private UserDAO UDAO =UserDAO.getInstance();

    @FXML
    private void login() throws IOException, JAXBException {
        String username = nickname_input.getText(); // Obtiene el nombre introducido

        User existingUser = UDAO.getUserByNickname(username);

        if (existingUser != null) {
            App.setRoot("Hub");
        } else {
            // El usuario no existe, muestra un diálogo de confirmación.
            boolean createNewUser = showConfirmationDialog("El usuario no existe. ¿Desea crearlo?");

            if (createNewUser) {
                User newUser = new User(username);
                UDAO.addUser(newUser);
                showAlert("Usuario creado con éxito.");
            }
        }
    }
    private boolean showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType yesButton = new ButtonType("Sí");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == yesButton;
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}