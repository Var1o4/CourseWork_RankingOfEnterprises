package com.example.demo4.Controllers;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EquityController extends BaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text companyDanger;

    @FXML
    private Text companyDanger1;

    @FXML
    private Text coverate;

    @FXML
    private Text equite_level;

    @FXML
    private TextField equity;

    @FXML
    private TextField equity_;

    @FXML
    private Button solveRoe;

    @FXML
    private TextField totalAsses;

    @FXML
    private TextField totalAsses_;

    @FXML
    void initialize() {
        solveRoe.setOnAction(event->{
            try {
                double equity1 = Double.parseDouble(equity.getText());
                double equity_1 = Double.parseDouble(equity_.getText());
                double totalAsses1 = Double.parseDouble(totalAsses.getText());
                double totalAsses_1 = Double.parseDouble(totalAsses_.getText());
                StringBuilder stringBuilder = new StringBuilder();

                // Проверка значения
                if (Double.isFinite(equity1) && equity1 != 0 &&
                        Double.isFinite(equity_1) && equity_1 != 0 &&
                        Double.isFinite(totalAsses1) && totalAsses1 != 0 &&
                        Double.isFinite(totalAsses_1) && totalAsses_1 != 0) {
                    companyDanger1.setOpacity(0);
                    companyDanger.setOpacity(0);

                    double equity_result = equity1/totalAsses1;

                    DecimalFormat decimalFormat = new DecimalFormat("#.###");
                    String formattedResult = decimalFormat.format(equity_result);

                    equite_level.setText(formattedResult);

                    double coverate_result= equity_1/totalAsses_1;

                    DecimalFormat decimalFormat_cb = new DecimalFormat("#.###");
                    String formattedResult_cb = decimalFormat.format(coverate_result);

                    stringBuilder.append("equity_create/");
                    stringBuilder.append(equity1).append("/");
                    stringBuilder.append(totalAsses1).append("/");
                    stringBuilder.append(equity_result);

                    String equity_create = stringBuilder.toString();

                    stringBuilder.setLength(0);

                    PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);


                    stringBuilder.append("coverate_create/");
                    stringBuilder.append(equity_1).append("/");
                    stringBuilder.append(totalAsses_1).append("/");
                    stringBuilder.append(coverate_result);

                    String coverate_create = stringBuilder.toString();

                    out.println(equity_create);
                    out.println(coverate_create);


                    coverate.setText(formattedResult_cb);
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
