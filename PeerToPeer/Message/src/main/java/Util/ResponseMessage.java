package Util;

import java.io.Serializable;

public class ResponseMessage implements Serializable {
    public static String STATUS_FAIL = "fail";
    public static String STATUS_SUCCESS = "success";

    String message;
    String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
