package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    static Socket socket;

    static {
        try {
            socket = new Socket("localhost", 2077);
            System.out.printf("is socket connected %s%n", socket.isConnected());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage() {

        OutputStream stream = null;
        try {
            if (socket.getInputStream().available() > 0) {
                var keepAlive = socket.getKeepAlive();
                var scanner = new Scanner(socket.getInputStream());
                System.out.printf("Ответ сервера: %s%n", scanner.nextLine());
            }

            stream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(stream, true);
            printWriter.println("hello, there");
            printWriter.flush();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
