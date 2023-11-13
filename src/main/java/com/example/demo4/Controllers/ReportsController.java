package com.example.demo4.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.demo4.Database.DAO.CompanyDataDAO;
import com.example.demo4.Database.TableInfo;
import com.example.demo4.User.SolveResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReportsController extends BaseController{


    private double roe_1, coverate_1, equity_1, dpo_1, dpoc_1, cb_1;
    private String company_1, res;


    public void ini(String companyName) throws SQLException {
        CompanyDataDAO companyDataDAO = new CompanyDataDAO();

        int companyId = companyDataDAO.findCompanyResult(companyName);

        if (companyId == -1) {
            System.out.println("Компания не найдена.");
        } else {
            // Компания найдена, продолжайте выполнение кода
            // ...
            TableInfo tableInfo = TableInfo.createTableInfoByCompanyId(companyId);
            this.roe_1 =tableInfo.getDoubleById("roe_data", "roe_id", "roe", tableInfo.getRoeTableId());
            this.cb_1 =tableInfo.getDoubleById("cb_rate", "cb_rate_id", "real_rate", tableInfo.getCbTableId());
            this.equity_1 =tableInfo.getDoubleById("equity_level", "equity_id", "equity_level", tableInfo.getCoverateTableId());
            this.coverate_1 =tableInfo.getDoubleById("coverate_ratio", "coverate_ratio_id", "coverate_ratio", tableInfo.getCoverateTableId());
            this.dpo_1 = tableInfo.getDoubleById("dpo_data", "dpo_data_id", "dpo", tableInfo.getDpoTableId());
            this.dpoc_1 = tableInfo.getDoubleById("dpoc_data", "dpoc_data_id", "dpoc", tableInfo.getDpocTableId());
            this.company_1 =tableInfo.getStringById("company", "company_name", "company_id", tableInfo.getCompanyTableId());
            this.res =tableInfo.getStringById("company", "result", "company_id", tableInfo.getCompanyTableId());
            this.fac= true;
        }

    }




    public void initial(double roe_1, double coverate_1, double equity_1, double dpo_1, double dpoc_1, double cb_1, String company_1, String res) {
        this.roe_1 = roe_1;
        this.coverate_1 = coverate_1;
        this.equity_1 = equity_1;
        this.dpo_1 = dpo_1;
        this.dpoc_1 = dpoc_1;
        this.cb_1 = cb_1;
        this.company_1 = company_1;
        this.res = res;
        this.fac=true;


    }
    boolean fac =false;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField company_name;

    @FXML
    private Button coverate_record;

    @FXML
    private Button dpo_record;

    @FXML
    private Button dpoc_record;

    @FXML
    private Button equity_report;

    @FXML
    private Button main_report;

    @FXML
    private Button roe_report;

    @FXML
    private Text danger;

    @FXML
    void initialize() {
        SolveResult solveResult = new SolveResult();
        main_report.setOnAction(event->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "result.fxml"));
                Parent root = loader.load();

                Stage infoStage = new Stage();
                infoStage.setTitle("Information");
                infoStage.setScene(new Scene(root));
                ini(company_name.getText());

                // Получение контроллера нового окна
                ResultController resultController = loader.getController();
                resultController.initial(roe_1, coverate_1, equity_1, dpo_1, dpoc_1, cb_1, company_1, res);
                resultController.setSocket(getSocket());

                // Отображение нового окна
                infoStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });






        roe_report.setOnAction(event->{
            try {


                if(fac){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "simple_report.fxml"));
                    Parent root = loader.load();

                    Stage infoStage = new Stage();
                    infoStage.setTitle("Information");
                    infoStage.setScene(new Scene(root));
                    // Получение контроллера нового окна
                    SimpleReport simpleReport = loader.getController();
                    simpleReport.setName_report_1("Рентабельность собственного капитала");
                    simpleReport.setVal(String.valueOf(roe_1));
                    simpleReport.setRes(solveResult.roeScoreSolve(roe_1, cb_1));
                    simpleReport.initialize();
                    infoStage.show();
                }



                // Отображение нового окна

            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        equity_report.setOnAction(event->{
            try {


                if(fac){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "simple_report.fxml"));
                    Parent root = loader.load();

                    Stage infoStage = new Stage();
                    infoStage.setTitle("Information");
                    infoStage.setScene(new Scene(root));
                    // Получение контроллера нового окна
                    SimpleReport simpleReport = loader.getController();
                    simpleReport.setName_report_1("Уровень собственного капитала");
                    simpleReport.setVal(String.valueOf(equity_1));
                    simpleReport.setRes(solveResult.equityScoreSolve(equity_1));
                    simpleReport.initialize();
                    infoStage.show();
                }



                // Отображение нового окна

            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        coverate_record.setOnAction(event->{
            try {


                if(fac){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "simple_report.fxml"));
                    Parent root = loader.load();

                    Stage infoStage = new Stage();
                    infoStage.setTitle("Information");
                    infoStage.setScene(new Scene(root));
                    // Получение контроллера нового окна
                    SimpleReport simpleReport = loader.getController();
                    simpleReport.setName_report_1("Коэффициент покрытия внеоборотных активов собственным капиталом");
                    simpleReport.setVal(String.valueOf(coverate_1));
                    simpleReport.setRes(solveResult.coverateScoreSolve(coverate_1));
                    simpleReport.initialize();
                    infoStage.show();
                }



                // Отображение нового окна

            } catch (IOException e) {
                e.printStackTrace();
            }
        });




        dpo_record.setOnAction(event->{
            try {


                if(fac){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "simple_report.fxml"));
                    Parent root = loader.load();

                    Stage infoStage = new Stage();
                    infoStage.setTitle("Information");
                    infoStage.setScene(new Scene(root));
                    // Получение контроллера нового окна
                    SimpleReport simpleReport = loader.getController();
                    simpleReport.setName_report_1("Длительность оборота кредиторской задолженности");
                    simpleReport.setVal(String.valueOf(dpo_1));
                    simpleReport.setRes(solveResult.dpoScoreSolve(dpo_1));
                    simpleReport.initialize();
                    infoStage.show();
                }



                // Отображение нового окна

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        dpoc_record.setOnAction(event->{
            try {


                if(fac){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/" + "simple_report.fxml"));
                    Parent root = loader.load();

                    Stage infoStage = new Stage();
                    infoStage.setTitle("Information");
                    infoStage.setScene(new Scene(root));
                    // Получение контроллера нового окна
                    SimpleReport simpleReport = loader.getController();
                    simpleReport.setName_report_1("Длительность оборота чистого производственного оборотного капитала");
                    simpleReport.setVal(String.valueOf(dpoc_1));
                    simpleReport.setRes(solveResult.dpocScoreSolve(dpoc_1));
                    simpleReport.initialize();
                    infoStage.show();
                }



                // Отображение нового окна

            } catch (IOException e) {
                e.printStackTrace();
            }
        });







    }




}
