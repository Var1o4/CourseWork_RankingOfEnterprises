package com.example.demo4.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import com.example.demo4.Server.ClientApp;
import javafx.stage.Stage;

public class WorkUserController extends BaseController {




    boolean roe = false;
    boolean equity = false;

    boolean credit = false;



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;



    @FXML
    void capital(MouseEvent event) {

        System.out.println(getUserId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/"  + "equity_level.fxml"));

        try {
            Parent root = loader.load();
            EquityController equityController = loader.getController();
            equityController.setUserId(getUserId());
            equityController.setIdStorage(getIdStorage());
            equityController.setSocket(getSocket());



            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void credit(MouseEvent event) {
        System.out.println(getUserId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/"  + "credit.fxml"));

        try {
            Parent root = loader.load();
            CreditController creditController = loader.getController();
            creditController.setUserId(getUserId());
            creditController.setIdStorage(getIdStorage());
            creditController.setSocket(getSocket());


            
            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void documents(MouseEvent event) {

    }

    @FXML
    void result(MouseEvent event) {

    }

    @FXML
    void roe(MouseEvent event) {
        System.out.println(getUserId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/"  + "roe.fxml"));

        try {
            Parent root = loader.load();
            RoeController roeController = loader.getController();
            roeController.setUserId(getUserId());
            roeController.setIdStorage(getIdStorage());
            roeController.setSocket(getSocket());

            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void show_company(MouseEvent event) {

    }

    @FXML
    void initialize() {



    }

}
