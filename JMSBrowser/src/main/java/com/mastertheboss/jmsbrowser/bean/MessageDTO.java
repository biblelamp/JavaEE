package com.mastertheboss.jmsbrowser.bean;

public class MessageDTO {
    public String id;
    public String message;
    public int priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public MessageDTO(String id, String message, int priority) {
        this.id = id;
        this.message= message;
        this.priority = priority;
    }

    public MessageDTO() {
    }
}
