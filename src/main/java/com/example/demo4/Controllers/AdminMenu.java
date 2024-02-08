package com.example.demo4.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.demo4.Database.JDBС;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

    public int getUserCount(String groupValue) throws SQLException {
        int count = 0;
        JDBС.connect();
        try {
            String selectSql = "SELECT COUNT(*) AS count FROM user WHERE role LIKE ?";
            PreparedStatement selectStatement = JDBС.connection.prepareStatement(selectSql);
            selectStatement.setString(1, "%" + groupValue + "%");
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }

            resultSet.close();
            selectStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBС.close();

        return count;
    }


    public int getCompanyCountByGroup(String groupValue) throws SQLException {
        int count = 0;
        JDBС.connect();
        try {
            String selectSql = "SELECT COUNT(*) AS count FROM company WHERE result LIKE ?";
            PreparedStatement selectStatement = JDBС.connection.prepareStatement(selectSql);
            selectStatement.setString(1, "%" + groupValue + "%");
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }

            resultSet.close();
            selectStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBС.close();

        return count;
    }

    @FXML
    void dora(MouseEvent event) throws SQLException {
        int groupA = getCompanyCountByGroup("Группа A");
        int groupB = getCompanyCountByGroup("Группа B");
        int groupC = getCompanyCountByGroup("Группа C");
        int groupD = getCompanyCountByGroup("Группа D");
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Группа A", groupA),
                new PieChart.Data("Группа B", groupB),
                new PieChart.Data("Группа C", groupC),
                new PieChart.Data("Группа D", groupD)
        );
        PieChart chart = new PieChart(data);

        // Создание нового окна
        Stage stage = new Stage();
        stage.setTitle("График");
        Scene scene = new Scene(new Group(chart), 600, 400);
        stage.setScene(scene);
        stage.show();
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
    void dora_dura(MouseEvent event) throws SQLException {
        int groupA = getUserCount("admin");
        int groupB = getUserCount("user");

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Админы", groupA),
                new PieChart.Data("Пользователи", groupB)
        );
        PieChart chart = new PieChart(data);

        // Создание нового окна
        Stage stage = new Stage();
        stage.setTitle("График");
        Scene scene = new Scene(new Group(chart), 600, 400);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void initialize() {

    }

}
