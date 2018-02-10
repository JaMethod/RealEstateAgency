package net;

import java.io.*;
import java.net.Socket;

/**
 * This class accepts and handles the server and clients data.
 */
public class ServerHandler implements Runnable{

    private Socket socket;

    /**
     * @param socket - client
     */
    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * Initializes output and input streams for the socket.
     */
    @Override
    public void run() {
        try (
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream())
        ) {
            System.out.println("New connection from " + socket.getRemoteSocketAddress().toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}