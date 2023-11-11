package com.example.demo4.Controllers;

import com.example.demo4.Database.IdStorage;

import java.net.Socket;

public class BaseController {
    private int userId;

    private Socket socket;


    private IdStorage idStorage;

    public int getUserId() {
        return userId;
    }

    public IdStorage getIdStorage() {
        return idStorage;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setIdStorage(IdStorage idStorage) {
        this.idStorage = idStorage;
    }

    public void updateIdStorage(IdStorage idStorage) {
        this.idStorage = idStorage;
    }




    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void updateUserId(int userId) {
        this.userId = userId;
    }
}