package com.example.demo4.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminMenu extends BaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    void bacccccc(MouseEvent event) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo4/adminModule.fxml"));
                Parent root = fxmlLoader.load();
                Scene newScene = new Scene(root);

                AdminMenuChoise adminMenuChoise = fxmlLoader.getController();
                adminMenuChoise.setUserId(getUserId());
                adminMenuChoise.setSocket(getSocket());
                adminMenuChoise.setRole(getRole());

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(newScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    @FXML
    void documents(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/born_admin.fxml"));

        try {
            Parent root = loader.load();
            BornController bornController = loader.getController();
            bornController.setUserId(getUserId());
            bornController.setSocket(getSocket());
            bornController.setRole(getRole());
            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void show_company(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/user_show.fxml"));

        try {
            Parent root = loader.load();
            UserController userController = loader.getController();
            userController.setUserId(getUserId());
            userController.setSocket(getSocket());
            userController.setRole(getRole());
            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void initialize() {

    }

}
