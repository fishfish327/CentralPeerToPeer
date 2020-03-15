package Util;

import java.io.Serializable;

public class RequestMessage implements Serializable {
    public static String OBTAIN = "obtain";
    public static String REGISTRY = "registry";
    public static String SEARCH = "search";

    String command;
    String message;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
