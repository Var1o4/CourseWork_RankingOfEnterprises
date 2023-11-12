package com.example.demo4.Server;

import com.example.demo4.Database.DAO.DataDAOManager;
import com.example.demo4.Database.TableInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;

public class temp {
    private static int portNumber = 12345; // Порт сервера
    private static int interval = 5000;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server listening on port " + portNumber);

            while (true) {
                // Принимаем входящее соединение от клиента
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Создаем новый поток для обработки запроса клиента
                Thread clientThread = new Thread(() -> {
                    try {
                        String url = "jdbc:mysql://127.0.0.1:3306/course_work_psp";
                        String username = "root";
                        String password = "koval1008";
                        Connection connection = DriverManager.getConnection(url, username, password);
                        out = new PrintWriter(clientSocket.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        String inputLine;
                        TableInfo tableInfo = new TableInfo();
                        DataDAOManager daoManager = new DataDAOManager();


                        Savepoint savepoint = null;
                        connection.setAutoCommit(false);
                        savepoint = connection.setSavepoint();
                        StringBuilder stringBuilder = new StringBuilder();

                        try {
                            while ((inputLine = in.readLine()) != null) {
                                System.out.println("Received message from client: " + inputLine);
                                String response = "Server received: " + inputLine;

                                String[] elements = inputLine.split("/");

                                if (elements[0].equals("roe_create")) {
                                    if (tableInfo.isRoeTableCreated()) {
                                        daoManager.deleteRoeDataById(tableInfo.getRoeTableId());
                                    }
                                    tableInfo.setRoeTableId(daoManager.insertRoeData(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]), Double.parseDouble(elements[3])));
                                    System.out.println("Таблица roe создана");
                                } else if (elements[0].equals("cb_create")) {

                                    if (tableInfo.isCbTableCreated()) {
                                        daoManager.deleteCbDataById(tableInfo.getCbTableId());
                                    }
                                    System.out.println("Таблица cb создана");
                                    tableInfo.setCbTableId(daoManager.insertCBData(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]), Double.parseDouble(elements[3])));
                                } else if (elements[0].equals("company_create")) {
                                    if (tableInfo.isCompanyTableCreated()) {
                                        daoManager.deleteCompanyById(tableInfo.getCompanyTableId());
                                    }
                                    System.out.println("Таблица company создана");
                                    tableInfo.setCompanyTableId(daoManager.insertCompany(elements[1], "", Integer.parseInt(elements[2])));
                                } else if (elements[0].equals("dpo_create")) {
                                    if (tableInfo.isDpoTableCreated()) {
                                        daoManager.deleteDpoDataById(tableInfo.getDpoTableId());
                                    }
                                    System.out.println("Таблица dpo создана");
                                    tableInfo.setDpoTableId(daoManager.insertDpoData(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]), Double.parseDouble(elements[2]), Double.parseDouble(elements[3])));
                                } else if (elements[0].equals("dpoc_create")) {
                                    if (tableInfo.isDpocTableCreated()) {
                                        daoManager.deleteDpocDataById(tableInfo.getDpocTableId());
                                    }
                                    System.out.println("Таблица dpoc создана");
                                    tableInfo.setDpocTableId(daoManager.insertDpocData(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]), Double.parseDouble(elements[3])));
                                } else if (elements[0].equals("equity_create")) {
                                    if (tableInfo.isEquityTableCreated()) {
                                        daoManager.deleteEquityById(tableInfo.getEquityTableId());
                                    }
                                    System.out.println("Таблица roe создана");
                                    tableInfo.setEquityTableId(daoManager.insertEquity(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]), Double.parseDouble(elements[3])));
                                } else if (elements[0].equals("coverate_create")) {
                                    if (tableInfo.isCoverateTableCreated()) {
                                        daoManager.deleteCoverageRatioDataById(tableInfo.getCoverateTableId());
                                    }
                                    System.out.println(" клиент вышел");
                                    tableInfo.setCoverateTableId(daoManager.insertCoverageRatioData(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]), Double.parseDouble(elements[3])));
                                }

                                if (elements[0].equals("result")) {
                                    if (tableInfo.areAllTablesCreated()) {
                                        if(tableInfo.getCreated()){
                                            tableInfo.deleteIndeficatorsDataById(tableInfo.getIndificators_id());
                                        }
                                        tableInfo.setIndificators_id(tableInfo.createSystemIndicator());
                                        stringBuilder.setLength(0);
                                        stringBuilder.append("result/");
                                        stringBuilder.append(tableInfo.getDoubleById("roe_data", "roe_id", "roe", tableInfo.getRoeTableId())).append("/");
                                        stringBuilder.append(tableInfo.getDoubleById("cb_rate", "cb_rate_id", "real_rate", tableInfo.getCbTableId())).append("/");
                                        stringBuilder.append(tableInfo.getDoubleById("equity_level", "equity_id", "equity_level", tableInfo.getCoverateTableId())).append("/");
                                        stringBuilder.append(tableInfo.getDoubleById("coverate_ratio", "coverate_ratio_id", "coverate_ratio", tableInfo.getCoverateTableId())).append("/");
                                        stringBuilder.append(tableInfo.getDoubleById("dpo_data", "dpo_data_id", "dpo", tableInfo.getDpoTableId())).append("/");
                                        stringBuilder.append(tableInfo.getDoubleById("dpoc_data", "dpoc_data_id", "dpoc", tableInfo.getDpocTableId())).append("/");
                                        stringBuilder.append(tableInfo.getStringById("company", "company_name", "company_id", tableInfo.getDpocTableId())).append("/");
                                        stringBuilder.append(tableInfo.getCompanyTableId());

                                        String respon = stringBuilder.toString();
                                        System.out.println(respon);
                                        out.println(respon);
                                    } else {
                                        out.println("NonEmpthy");
                                        System.out.println("nonEmpphty");
                                    }
                                }



                                if (elements[0].equals("new_company_write")) {
                                    tableInfo=null;
                                }



                                if ("exit".equals(inputLine)) {
                                    if (!tableInfo.areAllTablesCreated()) {
                                        connection.rollback(savepoint);
                                    }
                                    System.out.println(" клиент вышел");
                                }
                                connection.commit();
                                // Отправляем ответ клиенту
                            }
                        } catch (SocketException e) {
                            System.out.println("Client disconnected unexpectedly: " + e.getMessage());
                        }

                        // Закрываем соединение с базой данных
                        connection.close();
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    } finally {
                        // Закрываем соединение с клиентом
                        try {
                            clientSocket.close();
                            System.out.println("Client disconnected");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                // Запускаем поток для обработки клиента
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
