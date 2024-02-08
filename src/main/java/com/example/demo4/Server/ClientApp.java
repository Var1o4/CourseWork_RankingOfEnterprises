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
import java.net.SocketException;

import com.example.demo4.Database.IdStorage;
public class ClientApp extends Application implements RegistrationListener {
    private static final int DEFAULT_USER_ID = 0;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private Controller controller;

    private int userId = 0;

    public int getUserId() {
        return userId;
    }
    public void sendLoginToServer(String login) {
        if (out != null) {
            out.println(login);
        }
    }

    @Override
    public void onRegistrationSuccess(String login, int userId) {
        this.userId = userId; // Сохранение user_id
        sendLoginToServer(login);
        controller.updateUserId(userId);// Отправка логина на сервер
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        IdStorage idStorage = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo4/authorisation.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 700, 400));
        primaryStage.setOnCloseRequest(event -> {
            sendExitCommandToServer();
        });
        primaryStage.show();

        // Получаем контроллер из загруженного FXML-файла
        controller = loader.getController();
        controller.setRegistrationListener(this);

        if (getUserId() == 0) {
            controller.setUserId(DEFAULT_USER_ID);
        } else {
            controller.setUserId(getUserId());
        }

        controller.setIdStorage(idStorage);

        
        // Подключение к серверу
        String serverAddress = "127.0.0.1"; // Адрес сервера
        int serverPort = 12345; // Порт сервера

        try {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            controller.setSocket(socket);

//            startServerListener();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendExitCommandToServer() {
        if (out != null) {
            out.println("exit"); // Отправка команды "exit" на сервер
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

    private void startServerListener() {
        new Thread(() -> {
            String serverMessage;
            try {
                while ((serverMessage = in.readLine()) != null) {
                    processServerMessage(serverMessage);
                }
            } catch (SocketException e) {
                System.out.println("Socket was closed, stopping server listener");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void processServerMessage(String message) {
        // Обработка полученного сообщения от сервера
        System.out.println("Received message from server: " + message);
        // Дополнительная логика обработки сообщения...
    }

    public static void main(String[] args) {
        launch(args);
    }
}