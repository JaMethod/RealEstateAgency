package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Fabric class for multi thread access for clients to the server.
 */
public class MultiThreadServer implements Runnable{

    private int port;
    private ExecutorService executor;

    /**
     * @param port - value from 0 to 65535
     * @param nThreads - count of threads
     */
    public MultiThreadServer(int port, int nThreads) {
        this.port = port;
        executor = Executors.newFixedThreadPool(nThreads);
    }

    /**
     * Server socket is accepting clients and execute them one by one in new threads.
     */
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!serverSocket.isClosed()) {
                //wait for a client
                Socket socket = serverSocket.accept();
                //executes when client is accepted
                executor.execute(new ServerHandler(socket));
            }
            executor.shutdown(); //
            System.out.println("закрыт");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

