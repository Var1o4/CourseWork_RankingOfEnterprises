package com.example.demo4.Controllers;

import com.example.demo4.User.AuthResult;
import com.example.demo4.User.Authorisotion;
import com.example.demo4.User.RegistrationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller extends BaseController {

    private RegistrationListener registrationListener;

    public void setRegistrationListener(RegistrationListener listener) {
        this.registrationListener = listener;
    }

    String status = "authorisation";

    @FXML
    private Text uncorrectAuthorisation;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button singUpButton;

    @FXML
    void initialize() {

        logInButton.setOnAction(event -> {
            String login = loginField.getText();
            String password = passwordField.getText();

            AuthResult authResult = Authorisotion.authenticateUser(login, password);
            if (authResult.isAuthenticated()) {
                int userId = authResult.getUserId();
                if (registrationListener != null) {
                    registrationListener.onRegistrationSuccess(login, userId);
                    // Уведомление о регистрации
                }
                updateUserId(userId);

                if(authResult.getRole().equals("user")){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo4/work_user_module.fxml"));
                        Parent root = fxmlLoader.load();
                        Scene newScene = new Scene(root);

                        WorkUserController workUserController = fxmlLoader.getController();
                        workUserController.setUserId(userId);
                        workUserController.setSocket(getSocket());
                        workUserController.setRole(authResult.getRole());
                        workUserController.initialize();

                        Stage primaryStage = (Stage) singUpButton.getScene().getWindow();
                        primaryStage.setScene(newScene);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else if(authResult.getRole().equals("admin") || authResult.getRole().equals("test")){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo4/adminModule.fxml"));
                        Parent root = fxmlLoader.load();
                        Scene newScene = new Scene(root);

                        AdminMenuChoise adminMenuChoise = fxmlLoader.getController();
                        adminMenuChoise.setUserId(getUserId());;
                        adminMenuChoise.setSocket(getSocket());
                        adminMenuChoise.setRole(authResult.getRole());

                        Stage primaryStage = (Stage) singUpButton.getScene().getWindow();
                        primaryStage.setScene(newScene);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                // Пользователь аутентифицирован, выполняйте необходимые действия
            } else {
                // Пользователь не найден, изменить прозрачность элемента uncorrectAuthorisation
                uncorrectAuthorisation.setOpacity(1.0);
            }
        });



        singUpButton.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo4/registration.fxml"));
            try {
                Parent root = fxmlLoader.load();
                Scene newScene = new Scene(root);

                Stage primaryStage = (Stage) singUpButton.getScene().getWindow();

                primaryStage.setScene(newScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
