package org.example;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
     new TCPServer().start();

        var timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                TCPClient.sendMessage();
            }
        }, 1000, 1000);

    }
}