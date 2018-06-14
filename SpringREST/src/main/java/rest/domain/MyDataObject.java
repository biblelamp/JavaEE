package rest.domain;

import java.util.Calendar;

public class MyDataObject {
    private Calendar time;
    private String message;

    public MyDataObject(Calendar time, String message) {
        this.time = time;
        this.message = message;
    }

    public MyDataObject() {
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}