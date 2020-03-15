package index;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessageUtil {
    private Map<String, String> messageMap = new HashMap<>();
    static String STATUS = "status";
    static String MESSAGE = "message";

    public ResponseMessageUtil(){

    }


    public void setSTATUS(String command){
        messageMap.put(STATUS, command);
    }

    public void setMessage(String message){
        messageMap.put(MESSAGE, message);
    }

    public Map<String, String> getMessageMap(){
        return this.messageMap;
    }

}
