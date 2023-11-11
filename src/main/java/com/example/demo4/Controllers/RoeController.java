package com.example.demo4.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.text.DecimalFormat;

public class RoeController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField equity;

    @FXML
    private TextField inflation_rate;

    @FXML
    private TextField net_profit;


    @FXML
    private Text companyDanger1;

    @FXML
    private TextField nominal_rate;

    @FXML
    private Text real_rate;

    @FXML
    private Text roe;

    @FXML
    private Button solveCB;

    @FXML
    private Button solveRoe;



    @FXML
    private Text companyDanger;

    @FXML
    private TextField companyName;


    @FXML
    void initialize() {

        solveRoe.setOnAction(event->{
            try {
                double net_profit_1 = Double.parseDouble(net_profit.getText());
                double equity_1 = Double.parseDouble(equity.getText());
                double nominal_rate_1 = Double.parseDouble(nominal_rate.getText());
                double inflation_rate_1 = Double.parseDouble(inflation_rate.getText());
                String comp_name = companyName.getText();
                StringBuilder stringBuilder = new StringBuilder();

                // Проверка значения
                if (Double.isFinite(net_profit_1)&&Double.isFinite(equity_1)&&Double.isFinite(nominal_rate_1)&&Double.isFinite(inflation_rate_1)&&comp_name!="") {
                    companyDanger1.setOpacity(0);
                    companyDanger.setOpacity(0);

                    double result = net_profit_1 / equity_1;

                    DecimalFormat decimalFormat = new DecimalFormat("#.###");
                    String formattedResult = decimalFormat.format(result);

                    roe.setText(formattedResult);

                    double result_cb= nominal_rate_1-inflation_rate_1 ;

                    DecimalFormat decimalFormat_cb = new DecimalFormat("#.###");
                    String formattedResult_cb = decimalFormat.format(result_cb);

                    stringBuilder.append("roe_create/");
                    stringBuilder.append(net_profit_1).append("/");
                    stringBuilder.append(equity_1).append("/");
                    stringBuilder.append(result);

                    String roe_create = stringBuilder.toString();

                    stringBuilder.setLength(0);


                    stringBuilder.append("cb_create/");
                    stringBuilder.append(nominal_rate_1).append("/");
                    stringBuilder.append(inflation_rate_1).append("/");
                    stringBuilder.append(result_cb);

                    String cb_create = stringBuilder.toString();

                    stringBuilder.setLength(0);

                    stringBuilder.append("company_create/");
                    stringBuilder.append(comp_name).append("/");
                    stringBuilder.append(getUserId());

                    String company_create = stringBuilder.toString();

                    PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);

                    out.println(roe_create);
                    out.println(cb_create);
                    out.println(company_create);

                    System.out.println(roe_create);
                    System.out.println(cb_create);
                    System.out.println(company_create);

                    real_rate.setText(formattedResult_cb);
                } else {
                    companyDanger1.setOpacity(0);
                    companyDanger.setOpacity(1.0);
                }
            } catch (NumberFormatException e) {
                companyDanger.setOpacity(0);
               companyDanger1.setOpacity(1.0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });


    }

}
