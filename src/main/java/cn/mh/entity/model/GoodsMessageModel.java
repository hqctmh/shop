package cn.mh.entity.model;

import java.io.Serializable;

public class GoodsMessageModel implements Serializable {
    private String message;

    private String userName;

    private String pic;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
