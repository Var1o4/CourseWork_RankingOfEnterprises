package com.example.demo4.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.demo4.User.Authorisotion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegController extends BaseController{


    String status = "registration";

    @FXML
    private Text fieldDanger;

    @FXML
    private Text passwordDanger;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fioField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private PasswordField repeatePasswordField;

    @FXML
    void initialize() {

        registerButton.setOnAction(event -> {
            String fio = fioField.getText();
            String login = loginField.getText();
            String password = passwordField.getText();
            String repeatPassword = repeatePasswordField.getText();

            boolean isFieldsEmpty = fio.isEmpty() || login.isEmpty() || password.isEmpty() || repeatPassword.isEmpty();
            boolean isPasswordsMatch = password.equals(repeatPassword);

            if (isFieldsEmpty) {
                passwordDanger.setOpacity(0);
                fieldDanger.setOpacity(1);
            } else if (!isPasswordsMatch) {
                fieldDanger.setOpacity(0);
                passwordDanger.setOpacity(1);
            } else {
                fieldDanger.setOpacity(0);
                passwordDanger.setOpacity(0);
                fieldDanger.setText("");
                passwordDanger.setText("");
                try {
                    Authorisotion.registerUser(login,password,  fio,"user");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo4/authorisation.fxml"));
                try {
                    Parent root = fxmlLoader.load();
                    Scene newScene = new Scene(root);
                    Controller controller = fxmlLoader.getController();
                    controller.setSocket(getSocket());
                    Stage primaryStage = (Stage) registerButton.getScene().getWindow();

                    primaryStage.setScene(newScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        });

    }

}
