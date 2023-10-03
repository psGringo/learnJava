package org.example;

import java.io.IOException;
import java.net.*;

public class UDPSender {
    public static void sendMessage(String message) {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte[] data = message.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(data, data.length, address, 1050);
            datagramSocket.send(packet);

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
