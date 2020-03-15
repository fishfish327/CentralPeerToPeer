package index;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IndexServerTask {
    private Socket clientSocket;

    public static String COMMAND = "command";
    public static String REGISTRY = "registry";
    public static String SEARCH = "search";
    public static String MESSAGE = "message";
    public static String STATUS = "status";

    public IndexServerTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            responsePeer(clientSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<String> convertStringToList(String input) {
        String separator = ",";
        String[] strArray = input.split(separator);
        return Arrays.asList(strArray);
    }

    public void responsePeer(Socket clientSocket) throws IOException, ClassNotFoundException {
        ObjectInputStream in;
        ObjectOutputStream out;
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream((clientSocket.getInputStream()));
        Map<String, String> request = (HashMap<String, String>) in.readObject();
        // print host

        System.out.println("request ok, from host: " + clientSocket.getInetAddress().getHostAddress());
        ResponseMessageUtil response = new ResponseMessageUtil();
        if (request.get(COMMAND).equals(REGISTRY)) {
            String host = clientSocket.getInetAddress().getHostAddress();
            String message = request.get(MESSAGE);
            List<String> fileName = convertStringToList(message);
            //MetaData.registry(host, fileName);
            //response = new ResponseMessage(ResponseMessage.SUCCESS, null);
            response.setSTATUS("SUCCESS");
        } else if (request.get(COMMAND).equals(SEARCH)) {
            String fileName = request.get(MESSAGE);
            /*String targetHost = MetaData.search(fileName);
            if (targetHost != null) {
                response = new ResponseMessage(ResponseMessage.SUCCESS, targetHost);
            } else {
                response = new ResponseMessage(ResponseMessage.NOT_FOUND, null);
            }*/

        }
        out.writeObject(response.getMessageMap());
        // print reponse ok
        System.out.println("response ok");
        out.close();
        in.close();


    }
}
