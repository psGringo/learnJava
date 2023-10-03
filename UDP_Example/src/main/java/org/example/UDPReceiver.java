package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceiver {
    public static void Execute() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(1050);

            while (true) {
                var pack = new DatagramPacket(new byte[15], 15);
                datagramSocket.receive(pack);
                System.out.println(new String(pack.getData()));
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
