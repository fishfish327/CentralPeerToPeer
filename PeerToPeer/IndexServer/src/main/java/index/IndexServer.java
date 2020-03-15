package index;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IndexServer {
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private ServerSocket serverSocket;
    private int PORT = 8090;


    public void init() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    public void handleRequest() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                IndexServerTask task = new IndexServerTask(clientSocket);
                executor.submit(task);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        IndexServer indexServer = new IndexServer();
        indexServer.init();

    }
}
