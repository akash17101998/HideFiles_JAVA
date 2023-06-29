package org.example;


import org.example.db.MyConnection;
import org.example.views.Welcome;


public class Main {
    public static void main(String[] args) {
        Welcome w = new Welcome();
        do {
            w.welcomeScreen();
        }while (true);
    }
}