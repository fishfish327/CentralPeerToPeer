package Peer;

import java.util.HashMap;
import java.util.Map;

/*
使用HashMap来储存需要发送的信息
key 包括 command, message , status
 */
public class MessageUtil {
    private Map<String, String> messageMap = new HashMap<>();
    static String COMMAND = "command";
    static String MESSAGE = "message";
    static String STATUS = "status";

    public MessageUtil(){

    }
    public String getValue(String key){
        return messageMap.get(key);
    }

    public void setCommand(String command){
        messageMap.put(COMMAND, command);
    }

    public void setMessage(String message){
        messageMap.put(MESSAGE, message);
    }

    public void setStatus(String status){
        messageMap.put(STATUS, status);
    }
    public Map<String, String> getMessageMap(){
        return this.messageMap;
    }

}
