package uz.setapp.controller.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.setapp.entity.Message;
import uz.setapp.payload.ResMessage;


@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Object data;
    private ResMessage status;

    public Response(Object data, Message status) {
        this.data = data;
        this.status = new ResMessage(status.getCode(), status.getMessage());
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResMessage getMessage() {
        return status;
    }

    public void setMessage(Message message) {
        this.status =new ResMessage( message.getCode(), message.getMessage());
    }
}
