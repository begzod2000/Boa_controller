package uz.setapp.controller.models;

public class Status {
    private int code;
    private Object message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Status(int code, Object message) {
        this.code = code;
        this.message = message;
    }

    public Status() {
    }
}
