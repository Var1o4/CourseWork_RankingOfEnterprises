package com.example.demo4.Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    private static Timer timer;
    private static int interval = 5000;
    private static PrintWriter out;
    private static BufferedReader in;
    public static void main(String[] args) {
        int portNumber = 12345; // Порт сервера

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server listening on port " + portNumber);

            while (true) {
                // Принимаем входящее соединение от клиента
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Создаем потоки ввода-вывода для общения с клиентом
                 out = new PrintWriter(clientSocket.getOutputStream(), true);
                 in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                startServerMessageTimer();
                // Ждем сообщение от клиента и отправляем ответ
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received message from client: " + inputLine);

                    // Обрабатываем полученное сообщение
                    String response = "Server received: " + inputLine;

                    // Отправляем ответ клиенту
                    out.println(response);
                    System.out.println("Sent response to client: " + response);
                }


                // Закрываем соединение с клиентом
                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startServerMessageTimer() {
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Код для отправки сообщения с сервера
                sendServerMessage("Hello from server!");
            }
        }, 0, interval);
    }

    private static void sendServerMessage(String message) {
        out.println(message);
    }
}