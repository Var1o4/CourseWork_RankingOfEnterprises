package com.example.demo4.Controllers;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class ResultController extends BaseController{
    private double roe_1, coverate_1, equity_1, dpo_1, dpoc_1, cb_1;
    private String company_1, res;

    public void initial(double roe_1, double coverate_1, double equity_1, double dpo_1, double dpoc_1, double cb_1, String company_1, String res) {
        this.roe_1 = roe_1;
        this.coverate_1 = coverate_1;
        this.equity_1 = equity_1;
        this.dpo_1 = dpo_1;
        this.dpoc_1 = dpoc_1;
        this.cb_1 = cb_1;
        this.company_1 = company_1;
        this.res = res;

        company_name.setText(company_1);
        coverate.setText(String.valueOf(coverate_1));
        dpo.setText(String.valueOf(dpo_1));
        dpoc.setText(String.valueOf(dpoc_1));
        equity.setText(String.valueOf(equity_1));
        group.setText(res);
        roe.setText(String.valueOf(roe_1));
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text company_name;

    @FXML
    private Text coverate;

    @FXML
    private Text dpo;

    @FXML
    private Text dpoc;

    @FXML
    private Text equity;

    @FXML
    private Text group;

    @FXML
    private Text roe;

    @FXML
    private Button new_company;


    @FXML
    void initialize() {

        new_company.setOnAction(event -> {
            try {
                PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
                out.println("new_company_write");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });
    }

}
