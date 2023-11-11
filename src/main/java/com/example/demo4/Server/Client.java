package com.example.demo4.Server;

import com.example.demo4.Controllers.Controller;
import com.example.demo4.User.RegistrationListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Application implements RegistrationListener {


    private static Client instance;

    public static Client getInstance() {
        return instance;
    }


    public int getUserId() {
        return userId;
    }


    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private int userId;

    public void sendLoginToServer(String login) {
        if (out != null) {
            out.println(login);
        }
    }

    @Override
    public void onRegistrationSuccess(String login, int userId) {
        this.userId = userId; // Сохранение user_id
        sendLoginToServer(login); // Отправка логина на сервер
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        instance = this;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/authorisation.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 700, 400));
        primaryStage.show();

        // Получаем контроллер из загруженного FXML-файла
        Controller controller = loader.getController();
        controller.setRegistrationListener(this);

        // Подключение к серверу
        String serverAddress = "127.0.0.1"; // Адрес сервера
        int serverPort = 12345; // Порт сервера

        try {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Пример отправки сообщения на сервер
            String message = "Hello, server!";
            out.println(message);

            // Пример получения ответа от сервера
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Используйте полученные данные в JavaFX приложении
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        // Закрытие соединения при выходе из приложения
        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }
        if (socket != null) {
            socket.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}