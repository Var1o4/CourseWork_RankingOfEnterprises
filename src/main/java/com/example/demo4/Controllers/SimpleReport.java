package com.example.demo4.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SimpleReport {
    private String name_report_1, res, val;

    public String getName_report_1() {
        return name_report_1;
    }

    public void setName_report_1(String name_report_1) {
        this.name_report_1 = name_report_1;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text ball;

    @FXML
    private Text name_report;

    @FXML
    private Text result;

    @FXML
    void initialize() {

        name_report.setText(name_report_1);

        result.setText(val);
        ball.setText(res);

    }

}
