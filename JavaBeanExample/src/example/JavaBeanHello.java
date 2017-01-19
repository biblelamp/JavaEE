package example;

public class JavaBeanHello {
    private String  message = "JavaBean component welcomes you!";

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}