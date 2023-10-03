package org.example;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(() -> UDPReceiver.Execute());

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                UDPSender.sendMessage("hi, this is UPD");
            }
        }, 1000, 1000);
    }
}