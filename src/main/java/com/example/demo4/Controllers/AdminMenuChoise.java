package com.example.demo4.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMenuChoise extends BaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button admine;

    @FXML
    private Button user;

    @FXML
    void initialize() {
        user.setOnAction(event->{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo4/work_user_module.fxml"));
                Parent root = fxmlLoader.load();
                Scene newScene = new Scene(root);

                WorkUserController workUserController = fxmlLoader.getController();
                workUserController.setUserId(getUserId());
                workUserController.setSocket(getSocket());
                workUserController.setRole(getRole());
                workUserController.initialize();

                Stage primaryStage = (Stage) user.getScene().getWindow();
                primaryStage.setScene(newScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
