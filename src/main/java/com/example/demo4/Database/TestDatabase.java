package com.example.demo4.Database;
import java.sql.*;

public class TestDatabase {

    public static void main(String[] argv) {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            JDBС.connect();
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return;
        }

        JDBС.close();
    }
}


