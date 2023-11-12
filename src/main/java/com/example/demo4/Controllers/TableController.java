package com.example.demo4.Controllers;

import com.example.demo4.Database.Companies;
import com.example.demo4.Database.JDBС;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableController extends BaseController {

    @FXML
    private TableColumn<Companies, String> name;

    @FXML
    private TableColumn<Companies, String> result;

    private ObservableList<Companies> companiesList;
    @FXML
    private TableView<Companies> table;

    @FXML
    private TableColumn<Companies, Integer> year;

    @FXML
    private Button show_x;


    public List<Companies> getCompaniesByUserId(int userId) throws SQLException {
        JDBС.connect();
        List<Companies> companies = new ArrayList<>();
        try {
            String query = "SELECT * FROM company WHERE user_id = ?";
            PreparedStatement statement = JDBС.connection.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int companyId = resultSet.getInt("company_id");
                String companyName = resultSet.getString("company_name");
                String result = resultSet.getString("result");

                Companies company = new Companies(companyName, result, 2023);
                companies.add(company);
            }

            resultSet.close();
            statement.close();
        } finally {
            JDBС.close();
        }

        return companies;
    }

    @FXML
    void initialize() throws SQLException {
        show_x.setOnAction(event -> {


            companiesList = FXCollections.observableArrayList();

            // Получение данных из базы данных на основе user_id
            // Ваш user_id
            List<Companies> companiesData = null;
            try {
                companiesData = getCompaniesByUserId(getUserId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Добавление объектов модели данных в список данных таблицы
            companiesList.addAll(companiesData);

            // Установка списка данных таблицы
            table.setItems(companiesList);

            // Привязка значений модели данных к столбцам таблицы
            name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            result.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
            year.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());});

    }



}
