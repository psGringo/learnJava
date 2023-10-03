package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer extends Thread {

    private static ServerSocket serverSocket;

    static {
        try {
            serverSocket = new ServerSocket(2077);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void echo(String msg, OutputStream stream) {
        PrintWriter printWriter = new PrintWriter(stream, true);
        printWriter.println(msg);
        printWriter.flush();
    }

    @Override
    public void run() {

        try {
            Socket socket = null;
            while (true) {
                if (socket == null)
                    socket = serverSocket.accept();

                Scanner scanner = new Scanner(socket.getInputStream());
                var msg = scanner.nextLine();
                System.out.printf("Received on server: %s%n", msg);
                echo(msg, socket.getOutputStream());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
