package Peer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeerClient {
    /*
    指定用于共享的文件夹
     */
    private String filePath;
    private static String OBTAIN = "obtain";
    private static String REGISTRY = "registry";
    private static String SEARCH = "search";
    private static String STATUS = "status";
    public PeerClient(String filePath){
        this.filePath = filePath;
    }
    /*
    return : 返回指定文件夹下的文件名列表
     */
    private List<String> getFileInfo() {
        // tmp res
        return Arrays.asList("1.txt", "2.txt");
    }

    private String convertListTOString(List<String> input){
        String seperator = ",";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.size(); i++){
            sb.append(input.get(i));
            if(i < input.size() - 1) {
                sb.append(seperator);
            }
        }
        return sb.toString();
    }
    /*
    将 getfileInfo() 获取的信息注册到 indexserver
     */
    public String registryToServer(String IP, Integer PORT) throws IOException {
        Socket clientSocket;
        String responseStatus;
        String message = convertListTOString(getFileInfo());
        clientSocket = new Socket(IP, PORT);
        MessageUtil requestMessage = new MessageUtil();
        requestMessage.setCommand(REGISTRY);
        requestMessage.setMessage(message);
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        out.writeObject(requestMessage.getMessageMap());
        try {
            Map<String, String> responseMessageMap = (HashMap<String, String>) in.readObject();
            responseStatus = responseMessageMap.get(STATUS);
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            responseStatus = "fail";
            return responseStatus;
        } finally {
            in.close();
            out.close();
            clientSocket.close();
        }



        return responseStatus;


    }
    /*
    根据文件名查询对应 peer ip
     */
    public String searchServer(String fileName, String IP, Integer PORT){
           return null;
    }

    /*
    根据ip 和文件名获取对应文件
     */
    public String obtainFile(String fileName, String IP, Integer PORT){
           return null;
    }
}
