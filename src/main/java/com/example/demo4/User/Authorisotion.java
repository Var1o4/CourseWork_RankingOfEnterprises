package com.example.demo4.User;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.demo4.Database.JDBС;
public class Authorisotion {


    public static void registerUser(String login, String password, String profileName, String user) throws SQLException {
        JDBС.connect();

        try {

            String role;
            if (user.equals("user"))
            {
                role = "user";
            }else{
                role ="admin";
            }
            // Проверка на существование пользователя с заданным логином
            if (isUserExists(login)) {
                System.out.println("Пользователь с логином '" + login + "' уже существует.");
                return;
            }

            String userSql = "INSERT INTO user (login, password, role) VALUES (?, ?, ?)";
            String profileSql = "INSERT INTO profile (user_id, profile_name) VALUES (?, ?)";
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            PreparedStatement userStatement = JDBС.connection.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS);
            userStatement.setString(1, login);
            userStatement.setString(2, hashedPassword);
            userStatement.setString(3, role);

            userStatement.executeUpdate();

            ResultSet generatedKeys = userStatement.getGeneratedKeys();
            int userId = 0;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
                System.out.println("Новый пользователь зарегистрирован. user_id = " + userId);
            }

            userStatement.close();

            PreparedStatement profileStatement = JDBС.connection.prepareStatement(profileSql);
            profileStatement.setInt(1, userId);
            profileStatement.setString(2, profileName);

            profileStatement.executeUpdate();

            profileStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
    }

    private static boolean isUserExists(String login) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE login = ?";
        PreparedStatement statement = JDBС.connection.prepareStatement(sql);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0;
        }
        return false;
    }
    public static AuthResult authenticateUser(String login, String password) {
        try {
            JDBС.connect();  // Установить соединение с базой данных

            String query = "SELECT user_id, password, role FROM user WHERE login = ?"; // Добавить role в запрос
            PreparedStatement statement = JDBС.connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String hashedPassword = resultSet.getString("password");
                String role = resultSet.getString("role"); // Получить роль из результата
                boolean isAuthenticated = BCrypt.checkpw(password, hashedPassword);

                JDBС.close();

                return new AuthResult(userId, isAuthenticated, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new AuthResult(-1, false, "user");
    }

}