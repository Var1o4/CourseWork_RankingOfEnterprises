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

public class CreditController extends BaseController{



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField amount_prod;

    @FXML
    private Text companyDanger;

    @FXML
    private Text companyDanger1;

    @FXML
    private Text dpo;

    @FXML
    private Text dpoc;

    @FXML
    private TextField kz;

    @FXML
    private TextField net_prod;

    @FXML
    private TextField nzak;

    @FXML
    private Button solveRoe;

    @FXML
    void initialize() {
        solveRoe.setOnAction(event->{
            try {
                double kz_1 = Double.parseDouble(kz.getText());
                double nkaz_1 = Double.parseDouble(nzak.getText());
                double net_prod_1 = Double.parseDouble(net_prod.getText());
                double amount_prod_1 = Double.parseDouble(amount_prod.getText());
                StringBuilder stringBuilder = new StringBuilder();

                // Проверка значения
                if (Double.isFinite(kz_1)&&Double.isFinite(nkaz_1)&&Double.isFinite(net_prod_1)&&Double.isFinite(amount_prod_1)) {
                    companyDanger1.setOpacity(0);
                    companyDanger.setOpacity(0);

                    double dpo_1 = (kz_1*365) / nkaz_1;

                    DecimalFormat decimalFormat = new DecimalFormat("#.###");
                    String formattedResult = decimalFormat.format(dpo_1);

                    dpo.setText(formattedResult);

                    double dpoc_1= (net_prod_1+365)/amount_prod_1 ;

                    DecimalFormat decimalFormat_cb = new DecimalFormat("#.###");
                    String formattedResult_cb = decimalFormat.format(dpoc_1);

                    stringBuilder.append("dpo_create/");
                    stringBuilder.append(kz_1).append("/");
                    stringBuilder.append(nkaz_1).append("/");
                    stringBuilder.append(dpo_1);

                    String dpo_create = stringBuilder.toString();

                    stringBuilder.setLength(0);

                    PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);


                    stringBuilder.append("dpoc_create/");
                    stringBuilder.append(net_prod_1).append("/");
                    stringBuilder.append(amount_prod_1).append("/");
                    stringBuilder.append(dpoc_1);

                    String dpoc_create = stringBuilder.toString();

                    out.println(dpo_create);
                    out.println(dpoc_create);


                    dpoc.setText(formattedResult_cb);
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


