package uz.setapp.entity;

import lombok.EqualsAndHashCode;
import uz.setapp.entity.base.BaseEntity;


import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity

public class Message extends BaseEntity {
    private Integer code;
    private String message;

    public Message(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Message() {

    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
