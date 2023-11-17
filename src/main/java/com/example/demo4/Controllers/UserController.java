package com.example.demo4.Controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demo4.Database.Companies;
import com.example.demo4.Database.JDBС;
import com.example.demo4.Database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UserController extends BaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button delete_x;

    @FXML
    private Button find_usr;

    @FXML
    private Button show_x;

    @FXML
    private Text user_info;

    @FXML
    private TextField user_login;

    @FXML
    private TableView<User> table;

    private ObservableList<User> userList;

    @FXML
    private TableColumn<User, String> year;

    @FXML
    private Button sort;

    @FXML
    private Button update_user;

    @FXML
    private TableColumn<User, String> name;


    public void updateUserRole(String login) throws SQLException {
        JDBС.connect();

        try {
            String updateSql = "UPDATE user SET role = ? WHERE login = ?";
            PreparedStatement updateStatement = JDBС.connection.prepareStatement(updateSql);
            String newRole = "admin";

            updateStatement.setString(1, newRole);
            updateStatement.setString(2, login);

            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User role updated successfully.");
            } else {
                System.out.println("Failed to update user role. User not found.");
            }

            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
    }

    public List<User> getUsers() throws SQLException {
        JDBС.connect();
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM user";
            PreparedStatement statement = JDBС.connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String role = resultSet.getString("role");

                User user = new User(login, role);
                users.add(user);
            }

            resultSet.close();
            statement.close();
        } finally {
            JDBС.close();
        }

        return users;
    }

    public List<User> getSortedUsers() throws SQLException {
        JDBС.connect();
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM user ORDER BY login"; // Добавлено предложение ORDER BY для сортировки по полю "login"
            PreparedStatement statement = JDBС.connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String role = resultSet.getString("role");

                User user = new User(login, role);
                users.add(user);
            }

            resultSet.close();
            statement.close();
        } finally {
            JDBС.close();
        }

        return users;
    }

    public void deleteUserByLogin(String login) throws SQLException {
        JDBС.connect();
        try {
            String query = "DELETE FROM user WHERE login = ?";
            PreparedStatement statement = JDBС.connection.prepareStatement(query);
            statement.setString(1, login);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                // Запись с указанным логином не найдена, выполните требуемое действие
                System.out.println("Пользователь с логином " + login + " не найден.");
                return;
            }

            // Удаление связанных таблиц

            // Удаление из таблицы `system_indicators`
            String deleteSystemIndicatorsQuery = "DELETE FROM system_indicators WHERE company_id IN (SELECT company_id FROM company WHERE user_id IN (SELECT user_id FROM user WHERE login = ?))";
            PreparedStatement deleteSystemIndicatorsStatement = JDBС.connection.prepareStatement(deleteSystemIndicatorsQuery);
            deleteSystemIndicatorsStatement.setString(1, login);
            deleteSystemIndicatorsStatement.executeUpdate();
            deleteSystemIndicatorsStatement.close();

            // Удаление из таблицы `company`
            String deleteCompanyQuery = "DELETE FROM company WHERE user_id IN (SELECT user_id FROM user WHERE login = ?)";
            PreparedStatement deleteCompanyStatement = JDBС.connection.prepareStatement(deleteCompanyQuery);
            deleteCompanyStatement.setString(1, login);
            deleteCompanyStatement.executeUpdate();
            deleteCompanyStatement.close();

            // Удаление из таблицы `profile`
            String deleteProfileQuery = "DELETE FROM profile WHERE user_id IN (SELECT user_id FROM user WHERE login = ?)";
            PreparedStatement deleteProfileStatement = JDBС.connection.prepareStatement(deleteProfileQuery);
            deleteProfileStatement.setString(1, login);
            deleteProfileStatement.executeUpdate();
            deleteProfileStatement.close();

            statement.close();
        } finally {
            JDBС.close();
        }
    }
    @FXML
    void initialize() {
        show_x.setOnAction(event -> {


            userList = FXCollections.observableArrayList();

            // Получение данных из базы данных на основе user_id
            // Ваш user_id
            List<User> userData = null;
            try {
                userData = getUsers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Добавление объектов модели данных в список данных таблицы
            userList.addAll(userData);

            // Установка списка данных таблицы
            table.setItems(userList);

            // Привязка значений модели данных к столбцам таблицы
            name.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
            year.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        });


        sort.setOnAction(event -> {


            userList = FXCollections.observableArrayList();

            // Получение данных из базы данных на основе user_id
            // Ваш user_id
            List<User> userData = null;
            try {
                userData = getSortedUsers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Добавление объектов модели данных в список данных таблицы
            userList.addAll(userData);

            // Установка списка данных таблицы
            table.setItems(userList);

            // Привязка значений модели данных к столбцам таблицы
            name.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
            year.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        });


        delete_x.setOnAction(event -> {
            String log = user_login.getText();
            if (!log.isEmpty()) {
                try {
                    deleteUserByLogin(log);
                    userList = FXCollections.observableArrayList();

                    // Получение данных из базы данных на основе user_id
                    // Ваш user_id
                    List<User> userData = null;
                    try {
                        userData = getUsers();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    // Добавление объектов модели данных в список данных таблицы
                    userList.addAll(userData);

                    // Установка списка данных таблицы
                    table.setItems(userList);

                    // Привязка значений модели данных к столбцам таблицы
                    name.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
                    year.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {

            }
        });

        update_user.setOnAction(event -> {
            String log = user_login.getText();
            if (!log.isEmpty()) {
                try {
                    updateUserRole(log);
                    userList = FXCollections.observableArrayList();

                    // Получение данных из базы данных на основе user_id
                    // Ваш user_id
                    List<User> userData = null;
                    try {
                        userData = getUsers();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    // Добавление объектов модели данных в список данных таблицы
                    userList.addAll(userData);

                    // Установка списка данных таблицы
                    table.setItems(userList);

                    // Привязка значений модели данных к столбцам таблицы
                    name.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
                    year.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {

            }
        });

    }

}
