package com.example.demo4.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo4.User.SolveResult;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "equity_level.fxml"));

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "credit.fxml"));

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
    void result(MouseEvent event) throws IOException {
        SolveResult solveResult = new SolveResult();
        PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
        String inputLine;
        String res;
        res = null;
        StringBuilder stringBuilder = new StringBuilder();
        out.println("result");
        double roe = 0, coverate = 0, equity = 0, dpo = 0, dpoc = 0, cb = 0;
        String company = null;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            String[] elements = inputLine.split("/");
            if (inputLine.equals("NonEmpthy")) {


            } else if(elements[0].equals("result")) {
                roe = Double.parseDouble(elements[1]);
                cb = Double.parseDouble(elements[2]);
                equity = Double.parseDouble(elements[3]);
                coverate = Double.parseDouble(elements[4]);
                dpo = Double.parseDouble(elements[5]);
                dpoc = Double.parseDouble(elements[6]);
                company = elements[7];
                solveResult.equitySolve(equity);
                solveResult.coverateSolve(coverate);
                solveResult.dpocSolve(dpoc);
                solveResult.dpoSolve(dpo);
                solveResult.roeSolve(roe, cb);
                res = solveResult.resultShow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "result.fxml"));

                try {
                    Parent root = loader.load();
                    ResultController resultController = loader.getController();
                    resultController.initial(roe, coverate, equity, dpo, dpoc, cb, company, res);

                    bp.setCenter(root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            stringBuilder.setLength(0);
            break;
        }





    }

    @FXML
    void roe(MouseEvent event) {
        System.out.println(getUserId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "roe.fxml"));

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
