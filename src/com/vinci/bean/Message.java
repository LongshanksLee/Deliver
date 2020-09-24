package com.vinci.bean;

import java.util.Objects;

/**
 * @Author:Vinci_Ma
 * @Oescription: 用于发送消息，消息类
 * @Date Created in 2020-08-17-22:02
 * @Modified By:
 */
public class Message {
    //前端接受到的格式
    //{status:0,result:"",data:{}}

    //状态码:0表示成功，-1表示失败
    private int status;
    //给客户端回复的消息内容
    private String result;
    //消息所携带的一组数据
    private Object data;

    @Override
    public String toString() {
        return "Message{" +
                "status=" + status +
                ", result='" + result + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return status == message.status &&
                Objects.equals(result, message.result) &&
                Objects.equals(data, message.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, result, data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Message(int status, String result) {
        this.status = status;
        this.result = result;
    }

    public Message(String result) {
        this.result = result;
    }

    public Message(int status) {
        this.status = status;
    }

    public Message(int status, String result, Object data) {
        this.status = status;
        this.result = result;
        this.data = data;
    }

    public Message() {
    }
}
