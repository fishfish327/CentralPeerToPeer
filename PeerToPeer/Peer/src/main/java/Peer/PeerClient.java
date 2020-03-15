package Peer;

import Util.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;



public class PeerClient {
    /*
    指定用于共享的文件夹
     */
    private String filePath;

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
        String separator = ",";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.size(); i++){
            sb.append(input.get(i));
            if(i < input.size() - 1) {
                sb.append(separator);
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

        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setCommand(RequestMessage.REGISTRY);
        requestMessage.setMessage(message);
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        out.writeObject(requestMessage);
        try {
            ResponseMessage responseMessage = (ResponseMessage) in.readObject();
            responseStatus = responseMessage.getStatus();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            responseStatus = ResponseMessage.STATUS_FAIL;
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
    fileName: 需要查询的文件名, IP： IndexServer IP, PORT： IndexServer PORT
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
