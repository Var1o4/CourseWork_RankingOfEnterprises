package com.example.demo4.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.demo4.Database.DAO.CompanyDataDAO;
import com.example.demo4.User.SolveResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button jap;

    @FXML
    private Button japa;

    @FXML
    private Button japan;

    @FXML
    void bacccccc(MouseEvent event) {
        if (getRole().equals("user")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo4/authorisation.fxml"));
            try {
                Parent root = fxmlLoader.load();
                Scene newScene = new Scene(root);

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(newScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
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
    }


    @FXML
    void capital(MouseEvent event) {

        System.out.println(getUserId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "equity_level.fxml"));

        try {
            Parent root = loader.load();
            EquityController equityController = loader.getController();
            equityController.setUserId(getUserId());
            equityController.setSocket(getSocket());
            japa.setVisible(true);
            japa.setDisable(false);

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
            creditController.setSocket(getSocket());
            japan.setVisible(true);
            japan.setDisable(false);
            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void documents(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "reports.fxml"));

        try {
            Parent root = loader.load();
            ReportsController reportsController = loader.getController();
            reportsController.setUserId(getUserId());
            reportsController.setSocket(getSocket());

            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void result(MouseEvent event) throws IOException, SQLException {
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


            } else if (elements[0].equals("result")) {
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
                CompanyDataDAO companyDataDAO = new CompanyDataDAO();
                companyDataDAO.updateCompanyResult(Integer.parseInt(elements[8]), res);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "result.fxml"));

                try {
                    Parent root = loader.load();
                    ResultController resultController = loader.getController();
                    resultController.initial(roe, coverate, equity, dpo, dpoc, cb, company, res);

                    resultController.setSocket(getSocket());
                    resultController.setUserId(getUserId());
                    resultController.setRole(getRole());
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
            roeController.setSocket(getSocket());
            jap.setVisible(true);
            jap.setDisable(false);
            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void show_company(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/showCompanies.fxml"));

        try {
            Parent root = loader.load();
            TableController tableController = loader.getController();
            tableController.setUserId(getUserId());
            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {

        jap.setVisible(false);
        jap.setDisable(true);

        japa.setVisible(false);
        japa.setDisable(true);

        japan.setVisible(false);
        japan.setDisable(true);


    }

}
